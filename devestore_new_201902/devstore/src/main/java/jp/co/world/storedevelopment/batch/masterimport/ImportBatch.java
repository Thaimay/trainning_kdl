package jp.co.world.storedevelopment.batch.masterimport;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.SuppressPropertiesBeanIntrospector;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import jp.co.world.storedevelopment.Application;
import jp.co.world.storedevelopment.batch.masterimport.converter.CSVStringToBigDecimalConverter;
import jp.co.world.storedevelopment.batch.masterimport.converter.CSVStringToIntegerConverter;
import jp.co.world.storedevelopment.batch.masterimport.converter.CSVStringToLocalDateConverter;
import jp.co.world.storedevelopment.batch.masterimport.converter.CSVStringToLocalDateTimeConverter;
import jp.co.world.storedevelopment.batch.masterimport.converter.CSVStringToLongConverter;
import jp.co.world.storedevelopment.model.ActiveModel;

public abstract class ImportBatch<T extends ActiveModel<T>> {

	static {
		String mode = Application.mode();

		// if (mode == ImportBatch.MODE) { // TODO: bin utils とぶつかる問題があるため上手く解決したい
		// coordinationCreatedDatetime でコケるので一旦コメントアウトで対応
		ConvertUtils.register(new CSVStringToBigDecimalConverter(), BigDecimal.class);
		ConvertUtils.register(new CSVStringToIntegerConverter(), Integer.class);
		ConvertUtils.register(new CSVStringToLongConverter(), Long.class);
		ConvertUtils.register(new CSVStringToLocalDateConverter(), LocalDate.class);
		ConvertUtils.register(new CSVStringToLocalDateTimeConverter(), LocalDateTime.class);
		PropertyUtils.addBeanIntrospector(SuppressPropertiesBeanIntrospector.SUPPRESS_CLASS);
		PropertyUtils.clearDescriptors();
		// }
	}

	public static final String MODE = "batch";

	protected String baseCsvDir = Application.batchCsvDirPath();
	protected String backupCsvDir = baseCsvDir + "backup/";

	final private String csvFileName;

	final private List<String> fields;

	ImportBatch(String csvFileName, List<String> fields) {
		this.csvFileName = csvFileName;
		this.fields = fields;
	}

	private String getFilePath() {
		return String.format("%s/%s", baseCsvDir, csvFileName);
	}

	private String getFilePath(String csvDate) {
		return String.format("%s/%s/%s", backupCsvDir, csvDate, csvFileName);
	}

	protected File readCSVFile() {
		return new File(getFilePath());
	}

	protected void execute(String[] args) {
		if (!isRun()) {
			throw new IllegalStateException("バッチモードで実行してください [-Drun=batch]");
		}
		File csvFile = readCSVFile();
		CSVFormat format = crateFormat();
		updateFromCsv(csvFile, format);
		backupCsvFile(csvFile);

	}

	private CSVFormat crateFormat() {
		return CSVFormat.EXCEL.withIgnoreEmptyLines().withIgnoreSurroundingSpaces().withQuoteMode(QuoteMode.ALL)
				.withTrim().withEscape('&');// .withEscape('\\').withEscape('&');
	}

	protected void updateFromCsv(File csvFile, CSVFormat format) {
		try (CSVParser parser = CSVParser.parse(csvFile, Charset.forName("UTF-8"), format)) {
			List<T> list = createModelList(parser);			
			updateAll(list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected List<T> createModelList(CSVParser parser) throws IOException {
		List<T> list = new ArrayList<T>();

		for (CSVRecord record : parser.getRecords()) {		
			list.add(createModelByRecord(record));
		}
		return list;
	}

	protected void updateAll(List<T> list) {
		for (T c : list) {
			Optional<T> opt = findBy(c);
			if (opt.isPresent()) {
				Long id = opt.get().getId();
				copyModelForUpdate(opt.get(), c).update();
				c.setId(id);

			} else {
				c.create();
			}
			updateRelatedTable(c);
		}
	}

	protected void copyProperty(String key, String value, T model) {
		try {
			String escapeString = StringEscapeUtils.escapeSql(value);
			BeanUtils.copyProperty(model, key, escapeString);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
			System.err.println(String.format("csv変換中にエラーが発生しました: %s <- value", key, value));
		}
	}

	protected T createModelByRecord(CSVRecord record) {
		Map<String, String> map = new HashMap<String, String>();

		AtomicInteger i = new AtomicInteger();
		fields.forEach(f -> map.put(f, record.get(i.getAndIncrement())));

		T model = createModel();
		map.forEach((key, value) -> copyProperty(key, value, model));

		return model;
	}

	protected T copyModelForUpdate(T model, T copy) {
		Map<String, String> map = new HashMap<String, String>();

		fields.forEach(f -> {
			if (!f.isEmpty()) {
				try {
					map.put(f, BeanUtils.getProperty(copy, f));
				} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
					e.printStackTrace();
				}
			}
		});

		map.forEach((key, value) -> {
			try {
				BeanUtils.copyProperty(model, key, value);
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
		});

		model.setUpdateAccountCode("batch");
		model.setUpdateDatetime(LocalDateTime.now());

		return model;
	}

	protected void updateRelatedTable(T model) {

	}

	protected abstract Optional<T> findBy(T model);

	protected abstract T createModel();

	protected static List<String> asList(String... fields) {
		return Arrays.asList(fields);
	}

	protected void print(T model) {
		String to = new ReflectionToStringBuilder(model).toString();
		System.out.println("%%%");
		System.out.println(to);
		System.out.println("%%%%");
	}

	public static boolean isRun() {
		System.out.println(System.getProperty("run"));
		return StringUtils.equals(ImportBatch.MODE, System.getProperty("run"));
	}

	public void backupCsvFile(File csvFile) {
		try {
			Date date = new Date();
			String csvDate = new SimpleDateFormat("yyyyMMdd").format(date);
			FileUtils.forceMkdir(new File(backupCsvDir + csvDate));
			File csvBackupFile = new File(getFilePath(csvDate));
			FileUtils.moveFile(csvFile, csvBackupFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
