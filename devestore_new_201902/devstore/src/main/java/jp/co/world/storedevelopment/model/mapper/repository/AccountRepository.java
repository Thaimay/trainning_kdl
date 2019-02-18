package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;
import com.world.storedevelopment.utils.ZenkakuUtils;

import jp.co.world.storedevelopment.Application;
import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.mapper.AccountRepositoryMapper;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationRelationDTO;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationRelationFindByTextFormDTO;

public class AccountRepository extends Repository<Account, AccountRepositoryMapper> {

	@Override
	protected AccountRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(AccountRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return "I_" + ActiveModelStringUtils.toTableName(Account.class);
	}

	public List<NegotiationRelationDTO> findByText(NegotiationRelationFindByTextFormDTO dto) {
		return execute((mapper) -> {
			List<Account> list = mapper.findByText(dto, Application.SUGGEST_LIMIT_SIZE);
			List<Account> kanaList = mapper.findKanaByText(dto, Application.SUGGEST_LIMIT_SIZE);
			List<NegotiationRelationDTO> fullNames = list.stream()
					.map(a -> new NegotiationRelationDTO(a.getId(), ZenkakuUtils.toZenkaku(a.getFullName())))
					.collect(Collectors.toList());
			List<NegotiationRelationDTO> kanaNames = kanaList.stream()
					.map(a -> new NegotiationRelationDTO(a.getId(), ZenkakuUtils.toZenkaku(a.getKanaFullName())))
					.collect(Collectors.toList());

			fullNames.addAll(kanaNames);
			return fullNames;
		});
	}

	public Optional<Account> findByCode(String code) {
		return execute((mapper) -> {
			Account account = mapper.findByCode(code);
			return Optional.ofNullable(account);
		});
	}

	public Optional<Account> findByEmail(String email) {
		return execute((mapper) -> {
			Account account = mapper.findByEmail(email);
			return Optional.ofNullable(account);
		});

	}

	public List<Account> findCommon(Account account) {
		return execute((mapper) -> {
			return mapper.findCommon(account.getEmployeCode());
		});
	}
	
	public Optional<Account> findByImportCode(Account model) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findByImportCode(model.getEmployeeCd()));
		});
	}

	public List<String> areaCodesByAccountId(Long id) {
		return execute((mapper) -> {
			return mapper.areaCodesByAccountId(id);
		});
	}
	
}
