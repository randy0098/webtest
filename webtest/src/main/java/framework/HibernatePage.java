package framework;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

public class HibernatePage extends BaseDAOImpl{
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

	/**
	 * 
	 * 创建页面
	 * 
	 */
	public void createPage(){
		// 设置记录开始和截止序号
		// 记录序号从1开始
		// 当前页序号从1开始
		if (this.currentPageIndex > 0 && this.pageRecordNum > 0) {
			this.startRecordIndex = (this.currentPageIndex - 1) * this.pageRecordNum + 1;
			this.endRecordIndex = this.currentPageIndex * this.pageRecordNum;
			// 查询并保存查询记录结果集
			this.records = this.selectRecords();
			// 获得记录总数和总页数
			this.calculateTotalPage();
		}
		//此时查询不出任何的记录
		else if(this.currentPageIndex == 0){
			this.records = new ArrayList();
		}
	}

	/**
	 * 
	 * 计算得到总页数
	 * 
	 */
	public void calculateTotalPage() {
		// 获得记录总数
		this.selectRecordsNum();
		// 获得总页数
		if (this.pageRecordNum != 0) {
			if (this.recordNum % this.pageRecordNum == 0) {
				this.totalPage = this.recordNum / this.pageRecordNum;
			} else {
				this.totalPage = this.recordNum / this.pageRecordNum + 1;
			}
		}
	}

	/**
	 * 
	 * 查询记录总数
	 * 
	 */
	public void selectRecordsNum() {
		Long count = (Long)getHibernateTemplate().find(countSql).listIterator().next();
		//注意这里将记录数量的long值转成了int值
		this.recordNum = count.intValue();
	}

	/**
	 * 
	 * 查询数据库获得记录列表信息
	 * 
	 */
	private ArrayList selectRecords() {
		return (ArrayList)getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				// 创建query对象
				Query query = session.createQuery(querySql);
				// 返回其执行了分页方法的list
				return query.setFirstResult(startRecordIndex-1).setMaxResults(pageRecordNum).list();
			}
		});
	}

	/**
	 * 
	 * 转到首页
	 * 
	 */
	public void goToFirst() {
		this.setCurrentPageIndex(1);
		this.createPage();
	}

	/**
	 * 
	 * 转到尾页
	 * 
	 */
	public void goToLast() {
		// 获得记录总数和总页数
		this.calculateTotalPage();
		this.currentPageIndex = this.totalPage;
		this.createPage();
	}

	/**
	 * 
	 * 下一页
	 * 
	 */
	public void next(){
		// 获得记录总数和总页数
		this.calculateTotalPage();
		if (this.currentPageIndex < this.totalPage) {
			this.currentPageIndex = this.currentPageIndex + 1;
		} else {
			this.currentPageIndex = this.totalPage;
		}
		this.createPage();
	}

	/**
	 * 
	 * 上一页
	 * 
	 */
	public void back(){
		if (this.currentPageIndex > 1) {
			this.currentPageIndex = this.currentPageIndex - 1;
		} else {
			this.currentPageIndex = 1;
		}
		this.createPage();
	}

	/**
	 * 
	 * 转到第几页
	 * 
	 */
	public void go(int pageIndex) {
		// 获得记录总数和总页数
		this.calculateTotalPage();
		//注意这里如果查询不出记录时，this.totalPage=0，而CurrentPageIndex也会为0！
		if (pageIndex < 1) {
			pageIndex = 1;
		} else if (pageIndex>this.totalPage) {
			pageIndex = this.totalPage;
		}
		this.setCurrentPageIndex(pageIndex);
		this.createPage();
	}
	
	/**
	 * 
	 * 分页
	 * 
	 */
	public void paging(String action, String currentPageIndex) {
		// 默认显示第一页数据记录
		String action1 = "go";
		String currentPageIndex1 = "1";
		if (action != null && action.equalsIgnoreCase("") == false) {
			action1 = action;
		}
		if (currentPageIndex != null
				&& currentPageIndex.equalsIgnoreCase("") == false) {
			currentPageIndex1 = currentPageIndex;
		}
		if (action1.equalsIgnoreCase("goToFirst") == true) {
			this.goToFirst();
		} else if (action1.equalsIgnoreCase("goToLast") == true) {
			this.goToLast();
		} else if (action1.equalsIgnoreCase("back") == true) {
			this.setCurrentPageIndex(Integer.parseInt(currentPageIndex1));
			this.back();
		} else if (action1.equalsIgnoreCase("next") == true) {
			this.setCurrentPageIndex(Integer.parseInt(currentPageIndex1));
			this.next();
		} else if (action1.equalsIgnoreCase("go") == true) {
			this.go(Integer.parseInt(currentPageIndex1));
		}
	}

}
