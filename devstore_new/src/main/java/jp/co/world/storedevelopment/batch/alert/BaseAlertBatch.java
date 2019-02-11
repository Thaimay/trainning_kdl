package jp.co.world.storedevelopment.batch.alert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.ActiveModel;

abstract public class BaseAlertBatch<T extends ActiveModel<T>> {
	public void write() {
		start();
		execute();
		end();
	}
	
	public void start() {
		System.out.println(String.format("処理を開始します(%s)", getClass().getName()));
	};
	
	abstract public List<Account> targetAccount(T model);
	
	abstract public void createTodo(T t, Account a);
	
	abstract public void execute();
		
	public void end() {
		System.out.println(String.format("処理を終了します(%s)", getClass().getName()));		
	};
	
	public Boolean canCreate(List<T> list) {
		return list.size() > 0 && accountList(list).isEmpty() == false;
	}
	
	public void reserve(List<T> list) {
	}
	
	public void mail(List<T> list) {
	};
	
	public void todo(List<T> list) {
		System.out.println(String.format("処理処理対象は%s件です", list.size()));		
		System.out.println(String.format("対象のクラスは%sです", this.getClass().getGenericSuperclass().getTypeName()));
		System.out.println(String.format("対象IDは[%s]です", list.stream().map(s -> {
			return s.getId().toString();
		}).collect(Collectors.joining(","))));

		list.forEach(l -> {
			targetAccount(l).forEach(a -> {
				createTodo(l, a);
			});
		});
		reserve(list);
		mail(list);
	}
	
	public List<Account> allAccount(List<T> list) {
		List<Account> result = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			List<Account> accounts = targetAccount(list.get(i));
			for (int j = 0; j < accounts.size(); j++) {
				Account model = accounts.get(j);
				Integer size = result.stream().filter(v -> v.getId().equals(model.getId())).collect(Collectors.toList()).size();
				
				if (size == 0) {
					result.add(model);	
				}
			}
		}
		return result.stream().distinct().collect(Collectors.toList());
	}
	
	public List<T> targetProject(List<T> list, Account account) {
		List<T> result = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			List<Account> accounts = targetAccount(list.get(i)).stream().filter(v -> v.getId().equals(account.getId())).collect(Collectors.toList());
			if (accounts.size() > 0) {
				result.add(list.get(i));
			}
		}
		return result;
	}
	
	public String accountEmployeeCodes(List<T> list) {
		List<Account> result = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			List<Account> accounts = targetAccount(list.get(i));
			for (int j = 0; j < accounts.size(); j++) {
				result.add(accounts.get(j));
			}
		}
		return result.stream().map(a -> {
			return a.getMailAddress();
		}).distinct().collect(Collectors.joining(","));
	}

	public String accountList(List<T> list) {
		List<Account> result = projectToAccount(list);
		return result.stream().map(a -> {
			return a.getEmployeCode();
		}).distinct().collect(Collectors.joining(","));
	}
	
	public List<Account> projectToAccount(List<T> list) {
		List<Account> result = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			List<Account> accounts = targetAccount(list.get(i));
			for (int j = 0; j < accounts.size(); j++) {
				result.add(accounts.get(j));
			}
		}
		return result;
	}

}
