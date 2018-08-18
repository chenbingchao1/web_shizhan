package cn.itcast.Utils;

import java.io.Serializable;
import java.util.List;

/**
 * 分页工具类
 * 
 * @author Never Say Never
 * @date 2018年4月4日
 * @version V1.0
 */
public class PageBean<T> implements Serializable {

    // 分页查询的数据（查询得到）
    private List<T> rows;

    // 总记录数(查询得到)
    private int total;

    // 总页码数(计算得来)
    private int totalPage;

    // 起始索引(计算得来)
    private int startIndex;

    // 要查看的页码(用户在页面指定)
    private int pageNumber;

    // 每页显示大小(固定写死)
    private int pageSize;

    /**
     * 只要当前JavaBean中的total和pageSize有值了，那么就会自动计算totalPage
     * @return
     */
    public int getTotalPage() {
        return totalPage = this.total % this.pageSize == 0 ? (this.total / this.pageSize)
                : (this.total / this.pageSize + 1);
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    
    /**
     * 只要当前JavaBean中的pageNumber和pageSize有值了，那么就会自动计算startIndex
     * @return
     */
    public int getStartIndex() {
        return startIndex = (this.pageNumber-1)*this.pageSize;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public PageBean() {

    }

}
