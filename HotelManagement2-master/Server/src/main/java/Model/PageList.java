package Model;

import java.io.Serializable;
import java.util.List;

public class PageList<T> implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private List<T> listData;
	private int currentPage;
	private int maxPage;

	public List<T> getListData() {
		return listData;
	}

	public void setListData(List<T> listData) {
		this.listData = listData;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public PageList(List<T> listData, int currentPage, int maxPage) {
		super();
		this.listData = listData;
		this.currentPage = currentPage;
		this.maxPage = maxPage;
	}

	public PageList() {
		super();
	}

	@Override
	public String toString() {
		return "PageList [listDataSize=" + listData.size() + ", currentPage=" + currentPage + ", maxPage=" + maxPage + "]";
	}
	
	

}
