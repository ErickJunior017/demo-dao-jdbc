package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.DepatmentDao;
import model.entities.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class DepartmentDaoJDBC implements DepatmentDao {

    private Connection conn;

    public DepartmentDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void insert(Department obj) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try{

            st = conn.prepareStatement("INSERT INTO department (NAME) VALUES " +
                    "(?)",
                    Statement.RETURN_GENERATED_KEYS
            );

            st.setString(1, obj.getName());

            int rows = st.executeUpdate();

            if (rows > 0){
                rs = st.getGeneratedKeys();
                if(rs.next()) {
                    obj.setId(rs.getInt(1));
                    DB.closeResultSet(rs);
                }
            }else {
                throw new DbException("Error! null row affected");
            }
        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void update(Department obj) {
        PreparedStatement st = null;

        try{
            st = conn.prepareStatement("UPDATE department SET Name = ? WHERE Id = ?");
            st.setString(1, obj.getName());
            st.setInt(2, obj.getId());

            st.executeUpdate();

        } catch(SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deleteByID(Integer id) {
        PreparedStatement st = null;

        try{
            st = conn.prepareStatement("DELETE FROM department WHERE department.Id = ?");
            st.setInt(1, id);

            st.executeUpdate();

        } catch(SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
        }
    }

    public Department creteDepartment(ResultSet rs) throws SQLException{
        Department department = new Department();
        department.setName(rs.getString("Name"));
        department.setId(rs.getInt("Id"));
        return department;
    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try{
            st = conn.prepareStatement("SELECT department.* FROM department WHERE department.Id = ?");
            st.setInt(1, id);

            rs = st.executeQuery();

            if (rs.next()){
                Department department = creteDepartment(rs);
                return department;
            }
            return null;

        } catch(SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public List<Department> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        try{
            st = conn.prepareStatement("SELECT department.* FROM department " +
                    "ORDER BY Id"
            );


            rs = st.executeQuery();

            List<Department> departments = new ArrayList<>();
            while(rs.next()){
                Department department = creteDepartment(rs);
                departments.add(department);
            }
            return departments;

        } catch(SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
        }
    }
}
