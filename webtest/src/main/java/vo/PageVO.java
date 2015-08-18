package vo;

import java.util.ArrayList;

import framework.BaseVO;

public class PageVO extends BaseVO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 记录总数
	protected int recordNum;

	// 每页显示记录数量
	protected int pageRecordNum;

	// 页面数量
	protected int totalPage;

	// 当前页面序号
	protected int currentPageIndex;

	// 当前页面的记录集合
	protected ArrayList records = new ArrayList();

	// 本页开始记录序号
	protected int startRecordIndex;

	// 本页截止记录序号
	protected int endRecordIndex;

	// 原始查询语句
	protected String querySql;

	// 查询记录数量的sql语句
	protected String countSql;

	public int getRecordNum() {
		return recordNum;
	}

	public int getPageRecordNum() {
		return pageRecordNum;
	}

	public void setPageRecordNum(int pageRecordNum) {
		this.pageRecordNum = pageRecordNum;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public int getCurrentPageIndex() {
		return currentPageIndex;
	}

	public void setCurrentPageIndex(int currentPageIndex) {
		this.currentPageIndex = currentPageIndex;
	}

	public ArrayList getRecords() {
		return records;
	}

	public int getStartRecordIndex() {
		return startRecordIndex;
	}

	public int getEndRecordIndex() {
		return endRecordIndex;
	}

	public String getQuerySql() {
		return querySql;
	}

	public void setQuerySql(String querySql) {
		this.querySql = querySql;
	}

	public String getCountSql() {
		return countSql;
	}

	public void setCountSql(String countSql) {
		this.countSql = countSql;
	}

	public void setRecordNum(int recordNum) {
		this.recordNum = recordNum;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public void setRecords(ArrayList records) {
		this.records = records;
	}

	public void setStartRecordIndex(int startRecordIndex) {
		this.startRecordIndex = startRecordIndex;
	}

	public void setEndRecordIndex(int endRecordIndex) {
		this.endRecordIndex = endRecordIndex;
	}

	
}
