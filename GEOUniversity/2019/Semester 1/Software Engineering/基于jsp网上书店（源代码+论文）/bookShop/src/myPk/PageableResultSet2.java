package myPk;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.Ref;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;

public class PageableResultSet2 implements Pageable {
    protected java.sql.ResultSet rs=null;
    protected int rowsCount;
    protected int pageSize;
    protected int curPage;
    protected String command = "";
    
	public int getCurPage() {
		// TODO 自动生成方法存根
	    return curPage;
	}

	public int getPageCount() {
		// TODO 自动生成方法存根
	    if(rowsCount==0) return 0;
	    if(pageSize==0) return 1;
	    //calculate PageCount
	    double tmpD=(double)rowsCount/pageSize;
	    int tmpI=(int)tmpD;
	    if(tmpD>tmpI) tmpI++;
	    return tmpI;
	}

	public int getPageRowsCount() {
		// TODO 自动生成方法存根
	    if(pageSize==0) return rowsCount;
	    if(getRowsCount()==0) return 0;
	    if(curPage!=getPageCount()) return pageSize;
	    return rowsCount-(getPageCount()-1)*pageSize;
	}

	public int getPageSize() {
		// TODO 自动生成方法存根
	    return pageSize;
	}

	public int getRowsCount() {
		// TODO 自动生成方法存根
	    return rowsCount;
	}

	public void gotoPage(int page) {
		// TODO 自动生成方法存根
	    if (rs == null)
	        return;
	    if (page < 1)
	        page = 1;
	    if (page > getPageCount())
	        page = getPageCount();
	    int row = (page - 1) * pageSize + 1;
	    try {
	        rs.absolute(row);
	        curPage = page;
	    }
	    catch (java.sql.SQLException e) {
	    }
	}

	public void pageFirst() throws SQLException {
		// TODO 自动生成方法存根
	    int row=(curPage-1)*pageSize+1;
	    rs.absolute(row);
	}

	public void pageLast() throws SQLException {
		// TODO 自动生成方法存根
	    int row=(curPage-1)*pageSize+getPageRowsCount();
	    rs.absolute(row);
	}

	public void setPageSize(int pageSize) {
		// TODO 自动生成方法存根
	    if(pageSize>=0){
	        this.pageSize=pageSize;
	        curPage=1;
	    }
	}

	public boolean absolute(int row) throws SQLException {
		// TODO 自动生成方法存根
		return false;
	}

	public void afterLast() throws SQLException {
		// TODO 自动生成方法存根

	}

	public void beforeFirst() throws SQLException {
		// TODO 自动生成方法存根

	}

	public void cancelRowUpdates() throws SQLException {
		// TODO 自动生成方法存根

	}

	public void clearWarnings() throws SQLException {
		// TODO 自动生成方法存根

	}

	public void close() throws SQLException {
		// TODO 自动生成方法存根

	}

	public void deleteRow() throws SQLException {
		// TODO 自动生成方法存根

	}

	public int findColumn(String columnName) throws SQLException {
		// TODO 自动生成方法存根
		return 0;
	}

	public boolean first() throws SQLException {
		// TODO 自动生成方法存根
		return false;
	}

	public Array getArray(int i) throws SQLException {
		// TODO 自动生成方法存根
		return null;
	}

	public Array getArray(String colName) throws SQLException {
		// TODO 自动生成方法存根
		return null;
	}

	public InputStream getAsciiStream(int columnIndex) throws SQLException {
		// TODO 自动生成方法存根
		return null;
	}

	public InputStream getAsciiStream(String columnName) throws SQLException {
		// TODO 自动生成方法存根
		return null;
	}

	public BigDecimal getBigDecimal(int columnIndex) throws SQLException {
		// TODO 自动生成方法存根
		return null;
	}

	public BigDecimal getBigDecimal(String columnName) throws SQLException {
		// TODO 自动生成方法存根
		return null;
	}

	public BigDecimal getBigDecimal(int columnIndex, int scale)
			throws SQLException {
		// TODO 自动生成方法存根
		return null;
	}

	public BigDecimal getBigDecimal(String columnName, int scale)
			throws SQLException {
		// TODO 自动生成方法存根
		return null;
	}

	public InputStream getBinaryStream(int columnIndex) throws SQLException {
		// TODO 自动生成方法存根
		return null;
	}

	public InputStream getBinaryStream(String columnName) throws SQLException {
		// TODO 自动生成方法存根
		return null;
	}

	public Blob getBlob(int i) throws SQLException {
		// TODO 自动生成方法存根
		return null;
	}

	public Blob getBlob(String colName) throws SQLException {
		// TODO 自动生成方法存根
		return null;
	}

	public boolean getBoolean(int columnIndex) throws SQLException {
		// TODO 自动生成方法存根
		return false;
	}

	public boolean getBoolean(String columnName) throws SQLException {
		// TODO 自动生成方法存根
		return false;
	}

	public byte getByte(int columnIndex) throws SQLException {
		// TODO 自动生成方法存根
		return 0;
	}

	public byte getByte(String columnName) throws SQLException {
		// TODO 自动生成方法存根
		return 0;
	}

	public byte[] getBytes(int columnIndex) throws SQLException {
		// TODO 自动生成方法存根
		return null;
	}

	public byte[] getBytes(String columnName) throws SQLException {
		// TODO 自动生成方法存根
		return null;
	}

	public Reader getCharacterStream(int columnIndex) throws SQLException {
		// TODO 自动生成方法存根
		return null;
	}

	public Reader getCharacterStream(String columnName) throws SQLException {
		// TODO 自动生成方法存根
		return null;
	}

	public Clob getClob(int i) throws SQLException {
		// TODO 自动生成方法存根
		return null;
	}

	public Clob getClob(String colName) throws SQLException {
		// TODO 自动生成方法存根
		return null;
	}

	public int getConcurrency() throws SQLException {
		// TODO 自动生成方法存根
		return 0;
	}

	public String getCursorName() throws SQLException {
		// TODO 自动生成方法存根
		return null;
	}

	public Date getDate(int columnIndex) throws SQLException {
		// TODO 自动生成方法存根
		return null;
	}

	public Date getDate(String columnName) throws SQLException {
		// TODO 自动生成方法存根
		return null;
	}

	public Date getDate(int columnIndex, Calendar cal) throws SQLException {
		// TODO 自动生成方法存根
		return null;
	}

	public Date getDate(String columnName, Calendar cal) throws SQLException {
		// TODO 自动生成方法存根
		return null;
	}

	public double getDouble(int columnIndex) throws SQLException {
		// TODO 自动生成方法存根
		return 0;
	}

	public double getDouble(String columnName) throws SQLException {
		// TODO 自动生成方法存根
		return 0;
	}

	public int getFetchDirection() throws SQLException {
		// TODO 自动生成方法存根
		return 0;
	}

	public int getFetchSize() throws SQLException {
		// TODO 自动生成方法存根
		return 0;
	}

	public float getFloat(int columnIndex) throws SQLException {
		// TODO 自动生成方法存根
		return 0;
	}

	public float getFloat(String columnName) throws SQLException {
		// TODO 自动生成方法存根
		return 0;
	}

	public int getInt(int columnIndex) throws SQLException {
		// TODO 自动生成方法存根
		return 0;
	}

	public int getInt(String columnName) throws SQLException {
		// TODO 自动生成方法存根
		return 0;
	}

	public long getLong(int columnIndex) throws SQLException {
		// TODO 自动生成方法存根
		return 0;
	}

	public long getLong(String columnName) throws SQLException {
		// TODO 自动生成方法存根
		return 0;
	}

	public ResultSetMetaData getMetaData() throws SQLException {
		// TODO 自动生成方法存根
		return null;
	}

	public Object getObject(int columnIndex) throws SQLException {
		// TODO 自动生成方法存根
		return null;
	}

	public Object getObject(String columnName) throws SQLException {
		// TODO 自动生成方法存根
		return null;
	}

	public Object getObject(int arg0, Map<String, Class<?>> arg1)
			throws SQLException {
		// TODO 自动生成方法存根
		return null;
	}

	public Object getObject(String arg0, Map<String, Class<?>> arg1)
			throws SQLException {
		// TODO 自动生成方法存根
		return null;
	}

	public Ref getRef(int i) throws SQLException {
		// TODO 自动生成方法存根
		return null;
	}

	public Ref getRef(String colName) throws SQLException {
		// TODO 自动生成方法存根
		return null;
	}

	public int getRow() throws SQLException {
		// TODO 自动生成方法存根
		return 0;
	}

	public short getShort(int columnIndex) throws SQLException {
		// TODO 自动生成方法存根
		return 0;
	}

	public short getShort(String columnName) throws SQLException {
		// TODO 自动生成方法存根
		return 0;
	}

	public Statement getStatement() throws SQLException {
		// TODO 自动生成方法存根
		return null;
	}

	public String getString(int columnIndex) throws SQLException {
		// TODO 自动生成方法存根
		return null;
	}

	public String getString(String columnName) throws SQLException {
		// TODO 自动生成方法存根
	    try {
	        return rs.getString(columnName);
	    }
	    catch (SQLException e) 
	    {
	    	//这里是为了增加一些出错信息的内容便于调试
	        throw new SQLException (e.toString()+" columnName="+columnName+" SQL="+"this.getCommand()");
	    }
	}

	public Time getTime(int columnIndex) throws SQLException {
		// TODO 自动生成方法存根
		return null;
	}

	public Time getTime(String columnName) throws SQLException {
		// TODO 自动生成方法存根
		return null;
	}

	public Time getTime(int columnIndex, Calendar cal) throws SQLException {
		// TODO 自动生成方法存根
		return null;
	}

	public Time getTime(String columnName, Calendar cal) throws SQLException {
		// TODO 自动生成方法存根
		return null;
	}

	public Timestamp getTimestamp(int columnIndex) throws SQLException {
		// TODO 自动生成方法存根
		return null;
	}

	public Timestamp getTimestamp(String columnName) throws SQLException {
		// TODO 自动生成方法存根
		return null;
	}

	public Timestamp getTimestamp(int columnIndex, Calendar cal)
			throws SQLException {
		// TODO 自动生成方法存根
		return null;
	}

	public Timestamp getTimestamp(String columnName, Calendar cal)
			throws SQLException {
		// TODO 自动生成方法存根
		return null;
	}

	public int getType() throws SQLException {
		// TODO 自动生成方法存根
		return 0;
	}

	public URL getURL(int columnIndex) throws SQLException {
		// TODO 自动生成方法存根
		return null;
	}

	public URL getURL(String columnName) throws SQLException {
		// TODO 自动生成方法存根
		return null;
	}

	public InputStream getUnicodeStream(int columnIndex) throws SQLException {
		// TODO 自动生成方法存根
		return null;
	}

	public InputStream getUnicodeStream(String columnName) throws SQLException {
		// TODO 自动生成方法存根
		return null;
	}

	public SQLWarning getWarnings() throws SQLException {
		// TODO 自动生成方法存根
		return null;
	}

	public void insertRow() throws SQLException {
		// TODO 自动生成方法存根

	}

	public boolean isAfterLast() throws SQLException {
		// TODO 自动生成方法存根
		return false;
	}

	public boolean isBeforeFirst() throws SQLException {
		// TODO 自动生成方法存根
		return false;
	}

	public boolean isFirst() throws SQLException {
		// TODO 自动生成方法存根
		return false;
	}

	public boolean isLast() throws SQLException {
		// TODO 自动生成方法存根
		return false;
	}

	public boolean last() throws SQLException {
		// TODO 自动生成方法存根
		return false;
	}

	public void moveToCurrentRow() throws SQLException {
		// TODO 自动生成方法存根

	}

	public void moveToInsertRow() throws SQLException {
		// TODO 自动生成方法存根

	}

	public boolean next() throws SQLException {
		// TODO 自动生成方法存根
		return rs.next();
	}

	public boolean previous() throws SQLException {
		// TODO 自动生成方法存根
		return false;
	}

	public void refreshRow() throws SQLException {
		// TODO 自动生成方法存根

	}

	public boolean relative(int rows) throws SQLException {
		// TODO 自动生成方法存根
		return false;
	}

	public boolean rowDeleted() throws SQLException {
		// TODO 自动生成方法存根
		return false;
	}

	public boolean rowInserted() throws SQLException {
		// TODO 自动生成方法存根
		return false;
	}

	public boolean rowUpdated() throws SQLException {
		// TODO 自动生成方法存根
		return false;
	}

	public void setFetchDirection(int direction) throws SQLException {
		// TODO 自动生成方法存根

	}

	public void setFetchSize(int rows) throws SQLException {
		// TODO 自动生成方法存根

	}

	public void updateArray(int columnIndex, Array x) throws SQLException {
		// TODO 自动生成方法存根

	}

	public void updateArray(String columnName, Array x) throws SQLException {
		// TODO 自动生成方法存根

	}

	public void updateAsciiStream(int columnIndex, InputStream x, int length)
			throws SQLException {
		// TODO 自动生成方法存根

	}

	public void updateAsciiStream(String columnName, InputStream x, int length)
			throws SQLException {
		// TODO 自动生成方法存根

	}

	public void updateBigDecimal(int columnIndex, BigDecimal x)
			throws SQLException {
		// TODO 自动生成方法存根

	}

	public void updateBigDecimal(String columnName, BigDecimal x)
			throws SQLException {
		// TODO 自动生成方法存根

	}

	public void updateBinaryStream(int columnIndex, InputStream x, int length)
			throws SQLException {
		// TODO 自动生成方法存根

	}

	public void updateBinaryStream(String columnName, InputStream x, int length)
			throws SQLException {
		// TODO 自动生成方法存根

	}

	public void updateBlob(int columnIndex, Blob x) throws SQLException {
		// TODO 自动生成方法存根

	}

	public void updateBlob(String columnName, Blob x) throws SQLException {
		// TODO 自动生成方法存根

	}

	public void updateBoolean(int columnIndex, boolean x) throws SQLException {
		// TODO 自动生成方法存根

	}

	public void updateBoolean(String columnName, boolean x) throws SQLException {
		// TODO 自动生成方法存根

	}

	public void updateByte(int columnIndex, byte x) throws SQLException {
		// TODO 自动生成方法存根

	}

	public void updateByte(String columnName, byte x) throws SQLException {
		// TODO 自动生成方法存根

	}

	public void updateBytes(int columnIndex, byte[] x) throws SQLException {
		// TODO 自动生成方法存根

	}

	public void updateBytes(String columnName, byte[] x) throws SQLException {
		// TODO 自动生成方法存根

	}

	public void updateCharacterStream(int columnIndex, Reader x, int length)
			throws SQLException {
		// TODO 自动生成方法存根

	}

	public void updateCharacterStream(String columnName, Reader reader,
			int length) throws SQLException {
		// TODO 自动生成方法存根

	}

	public void updateClob(int columnIndex, Clob x) throws SQLException {
		// TODO 自动生成方法存根

	}

	public void updateClob(String columnName, Clob x) throws SQLException {
		// TODO 自动生成方法存根

	}

	public void updateDate(int columnIndex, Date x) throws SQLException {
		// TODO 自动生成方法存根

	}

	public void updateDate(String columnName, Date x) throws SQLException {
		// TODO 自动生成方法存根

	}

	public void updateDouble(int columnIndex, double x) throws SQLException {
		// TODO 自动生成方法存根

	}

	public void updateDouble(String columnName, double x) throws SQLException {
		// TODO 自动生成方法存根

	}

	public void updateFloat(int columnIndex, float x) throws SQLException {
		// TODO 自动生成方法存根

	}

	public void updateFloat(String columnName, float x) throws SQLException {
		// TODO 自动生成方法存根

	}

	public void updateInt(int columnIndex, int x) throws SQLException {
		// TODO 自动生成方法存根

	}

	public void updateInt(String columnName, int x) throws SQLException {
		// TODO 自动生成方法存根

	}

	public void updateLong(int columnIndex, long x) throws SQLException {
		// TODO 自动生成方法存根

	}

	public void updateLong(String columnName, long x) throws SQLException {
		// TODO 自动生成方法存根

	}

	public void updateNull(int columnIndex) throws SQLException {
		// TODO 自动生成方法存根

	}

	public void updateNull(String columnName) throws SQLException {
		// TODO 自动生成方法存根

	}

	public void updateObject(int columnIndex, Object x) throws SQLException {
		// TODO 自动生成方法存根

	}

	public void updateObject(String columnName, Object x) throws SQLException {
		// TODO 自动生成方法存根

	}

	public void updateObject(int columnIndex, Object x, int scale)
			throws SQLException {
		// TODO 自动生成方法存根

	}

	public void updateObject(String columnName, Object x, int scale)
			throws SQLException {
		// TODO 自动生成方法存根

	}

	public void updateRef(int columnIndex, Ref x) throws SQLException {
		// TODO 自动生成方法存根

	}

	public void updateRef(String columnName, Ref x) throws SQLException {
		// TODO 自动生成方法存根

	}

	public void updateRow() throws SQLException {
		// TODO 自动生成方法存根

	}

	public void updateShort(int columnIndex, short x) throws SQLException {
		// TODO 自动生成方法存根

	}

	public void updateShort(String columnName, short x) throws SQLException {
		// TODO 自动生成方法存根

	}

	public void updateString(int columnIndex, String x) throws SQLException {
		// TODO 自动生成方法存根

	}

	public void updateString(String columnName, String x) throws SQLException {
		// TODO 自动生成方法存根

	}

	public void updateTime(int columnIndex, Time x) throws SQLException {
		// TODO 自动生成方法存根

	}

	public void updateTime(String columnName, Time x) throws SQLException {
		// TODO 自动生成方法存根

	}

	public void updateTimestamp(int columnIndex, Timestamp x)
			throws SQLException {
		// TODO 自动生成方法存根

	}

	public void updateTimestamp(String columnName, Timestamp x)
			throws SQLException {
		// TODO 自动生成方法存根

	}

	public boolean wasNull() throws SQLException {
		// TODO 自动生成方法存根
		return false;
	}

	public PageableResultSet2(java.sql.ResultSet rs) throws java.sql.SQLException {
	    if(rs==null) throw new SQLException("given ResultSet is NULL","user");
	    
	    rs.last();
	    rowsCount=rs.getRow();
	    rs.beforeFirst();
	    
	    this.rs=rs;
	}  	

}
