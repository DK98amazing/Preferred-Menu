package commoncom.pmenu.comon.api;

import java.util.List;
import java.util.Set;

public interface PreferredMenuMapper<T> {

	Integer add(T t);

	Integer addList(List<T> tList);

	Integer remove(T t);

	Integer removeList(List<T> tList);

	Integer update(String key);

	Integer updateAll();

	T select(String key);

	Set<T> selectAll();
}
