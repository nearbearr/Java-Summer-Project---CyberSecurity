/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cybersecurityserver.alpha;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *This Class contains methods that are involved in different operations on Server Side
 */
public class DBOperations {

    //----------------------------------------------------------------------
    //                               Login Related
    //----------------------------------------------------------------------
     /**
     * This method is used for user authentication process  while Logging in
     * @param userName
     * @param password
     * @return UsermasterBean
     */
    public UsermasterBean authenticateUser(String userName, String password) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        UsermasterBean objBean = null;
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement("select * from usermaster where Username = ? and User_Type='Administrator'");
            pstmt.setString(1, userName);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                if (rs.getString("password").equals(password)) {
                    objBean = new UsermasterBean();
                    objBean.setUserId(rs.getInt("User_ID"));
                    objBean.setUsername(rs.getString("Username"));
                    objBean.setPassword(rs.getString("Password"));
                    objBean.setUserType(rs.getString("User_Type"));
                    objBean.setUserStatus(rs.getString("User_Status"));
                    objBean.setName(rs.getString("Name"));
                    objBean.setContactNumber(rs.getString("Contact_Number"));
                    objBean.setEmail(rs.getString("Email"));
                }
            }
        } catch (Exception e) {
            System.out.println("authenticateUser(String userName, String password) : of DBoperations" + e);
        } finally {
            try {
                rs.close();
                pstmt.close();
                conn.close();
            } catch (Exception e) {
                System.out.println("authenticateUser(String userName, String password) : of DBoperations" + e);
            }
        }
        return objBean;
    }
/**
     * This Method is used to get Users Detail According to their username and will return the reference of type UsermasterBean
     * @param userName
     * @return UsermasterBean
     */
    public UsermasterBean getUserDetailByUsername(String userName) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        UsermasterBean objBean = null;
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement("select * from usermaster where Username = ?");
            pstmt.setString(1, userName);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                objBean = new UsermasterBean();
                objBean.setUserId(rs.getInt("User_ID"));
                objBean.setUsername(rs.getString("Username"));
                objBean.setPassword(rs.getString("Password"));
                objBean.setUserType(rs.getString("User_Type"));
                objBean.setUserStatus(rs.getString("User_Status"));
                objBean.setEmail(rs.getString("Email"));
                objBean.setName(rs.getString("Name"));
                objBean.setContactNumber(rs.getString("Contact_Number"));
            }
        } catch (Exception e) {
            System.out.println("getUserDetailByUsername(String userName)  of DBoperations : " + e);
        } finally {
            try {
                rs.close();
                pstmt.close();
                conn.close();
            } catch (Exception e) {
                System.out.println("getUserDetailByUsername(String userName) of DBoperations : " + e);
            }
        }
        return objBean;
    }
/**
     * This method is used to add a new record of a user Activity and returns a UserActivityId
     * @param userId
     * @return Integer
     */
    public int addUserActivity(int userId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int userActivityId = 0;
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement("insert into useractivitymaster ( User_ID ,Login_Time,Grab_Folder) values(?,?,?) ");
            pstmt.setInt(1, userId);
            pstmt.setString(2, getCurrentDateTime());
            pstmt.setString(3, "");
            int i = pstmt.executeUpdate();
            if (i > 0) {
                pstmt = conn.prepareStatement("select max(Activity_ID) from useractivitymaster");
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    userActivityId = rs.getInt(1);
                }
            }
        } catch (Exception e) {
            System.out.println("addUserActivity(int userId) of DBoperations : " + e);
        } finally {
            try {
                rs.close();
                pstmt.close();
                conn.close();
            } catch (Exception e) {
                System.out.println("addUserActivity(int userId) of DBoperations : " + e);
            }
        }
        return userActivityId;
    }
/**
     * This method is Used to update UserActivity Record in which logout time of user will be updated corresponding to Activity Id
     * @param userActivityId 
     */
    public void updateUserActivity(int userActivityId) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement("update useractivitymaster set Logout_Time = ? where Activity_ID=?");
            pstmt.setString(1, getCurrentDateTime());
            pstmt.setInt(2, userActivityId);

            int i = pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("addUserActivity(int userId) of DBoperations : " + e);
        } finally {
            try {
                pstmt.close();
                conn.close();
            } catch (Exception e) {
                System.out.println("addUserActivity(int userId) of DBoperations : " + e);
            }
        }
    }
    /**
     * This method updates UserActivityRecord in Which path of Grabbed data is updated corresponding to ActivityID 
     * @param userActivityId
     * @param grabFolder 
     */

    public void updateUserActivity(int userActivityId,String grabFolder) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement("update useractivitymaster set Grab_Folder = ? where Activity_ID=?");
            pstmt.setString(1, grabFolder);
            pstmt.setInt(2, userActivityId);

            int i = pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("updateUserActivity(int userActivityId,String grabFolder) of DBoperations : " + e);
        } finally {
            try {
                pstmt.close();
                conn.close();
            } catch (Exception e) {
                System.out.println("updateUserActivity(int userActivityId,String grabFolder) of DBoperations : " + e);
            }
        }
    }
    /**
     * this method returns all User Activity Records
     * @return ArrayList
     */

    public ArrayList getAllUserActivityDetailList() {
        Connection conn = null;
        ArrayList alstUserActivity = new ArrayList();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement("select uam.Activity_ID,uam.User_ID,um.Username,um.Name, uam.Login_Time,uam.Logout_Time,uam.Grab_Folder  from usermaster um,useractivitymaster uam where um.User_Id=uam.User_Id");
            rs = pstmt.executeQuery();

            while (rs.next()) {
                {
                    UserActivityMasterBean objBean = new UserActivityMasterBean();
                    objBean.setUserId(rs.getInt("User_ID"));
                    objBean.setActivityId(rs.getInt("Activity_ID"));
                    objBean.setUsername(rs.getString("Username"));
                    objBean.setLoginTime(rs.getString("Login_Time"));
                    objBean.setLogoutTime(rs.getString("Logout_Time"));
                    objBean.setGrabFolder(rs.getString("Grab_Folder"));
                    objBean.setName(rs.getString("Name"));
                    alstUserActivity.add(objBean);
                }
            }
        } catch (Exception e) {
            System.out.println("getAllUserActivityDetailList() of DBoperations : " + e);
        } finally {
            try {
                rs.close();
                pstmt.close();
                conn.close();
            } catch (Exception e) {
                System.out.println("getAllUserActivityDetailList() of DBoperations : " + e);
            }
        }
        return alstUserActivity;
    }
/**
     * This method returns All Records of User's Activity where records are get corresponding to username
     * @param username
     * @return ArrayList
     */
    public ArrayList getAllUserActivityDetailListByUsername(String username) {
        Connection conn = null;
        ArrayList alstUserActivity = new ArrayList();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement("select uam.Activity_ID,uam.User_ID,um.Username,um.Name, uam.Login_Time,uam.Logout_Time,uam.Grab_Folder from usermaster um,useractivitymaster uam where um.User_Id=uam.User_Id and um.Username=?");
            pstmt.setString(1, username);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                {
                    UserActivityMasterBean objBean = new UserActivityMasterBean();
                    objBean.setUserId(rs.getInt("User_ID"));
                    objBean.setActivityId(rs.getInt("Activity_ID"));
                    objBean.setUsername(rs.getString("Username"));
                    objBean.setLoginTime(rs.getString("Login_Time"));
                    objBean.setLogoutTime(rs.getString("Logout_Time"));
                      objBean.setGrabFolder(rs.getString("Grab_Folder"));
                      objBean.setName(rs.getString("Name"));
                    alstUserActivity.add(objBean);
                }
            }
        } catch (Exception e) {
            System.out.println("getAllUserActivityDetailListByUsername(String username)  of DBoperations : " + e);
        } finally {
            try {
                rs.close();
                pstmt.close();
                conn.close();
            } catch (Exception e) {
                System.out.println("getAllUserActivityDetailListByUsername(String username)  of DBoperations : " + e);
            }
        }
        return alstUserActivity;
    }

    //-------------------------------------------------------------------------
    //                                 UserAccountDetail Related
    //-------------------------------------------------------------------------
   /**
     * This method is  used to get list of All Usernames and will return reference of type ArrayList
     * @return ArrayList
     */
    public ArrayList getAllUserNameList() {
        Connection conn = null;
        ArrayList alstUser = new ArrayList();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement("select Username from usermaster");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                alstUser.add(rs.getString("Username"));
            }
        } catch (Exception e) {
            System.out.println("getAllUserList() of DBoperations : " + e);
        } finally {
            try {
                rs.close();
                pstmt.close();
                conn.close();
            } catch (Exception e) {
                System.out.println("getAllUserList() of DBoperations : " + e);
            }
        }
        return alstUser;
    }
/**
     * This method is used to get Details of All Users and will return reference of type ArrayList
     * @return ArrayList
     */
    public ArrayList getAllUserDetailList() {
        Connection conn = null;
        ArrayList alstUser = new ArrayList();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement("select User_ID,Username,Password,User_Type,User_Status,Name ,Email,Contact_Number from usermaster");
            rs = pstmt.executeQuery();

            while (rs.next()) {
                {
                    UsermasterBean objBean = new UsermasterBean();
                    objBean.setUserId(rs.getInt("User_ID"));
                    objBean.setUsername(rs.getString("Username"));
                    objBean.setPassword(rs.getString("Password"));
                    objBean.setUserType(rs.getString("User_Type"));
                    objBean.setUserStatus(rs.getString("User_Status"));
                    objBean.setEmail(rs.getString("Email"));
                    objBean.setName(rs.getString("Name"));
                    objBean.setContactNumber(rs.getString("Contact_Number"));
                    alstUser.add(objBean);
                }
            }
        } catch (Exception e) {
            System.out.println("getAllUserDetailList() of DBoperations : " + e);
        } finally {
            try {
                rs.close();
                pstmt.close();
                conn.close();
            } catch (Exception e) {
                System.out.println("getAllUserDetailList() of DBoperations : " + e);
            }
        }
        return alstUser;
    }
/**
     * This method will get Account Detail of User according to userId and will return reference of type UsermasterBean
     * @param userId
     * @return UsermasterBean
     */
    public UsermasterBean getUserAccountDetailByUserId(int userId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        UsermasterBean objBean = new UsermasterBean();
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement("select User_ID,Username,Password,User_Type,User_Status,Name, Email,Contact_Number from usermaster where User_Id = ?");
            pstmt.setInt(1, userId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                {

                    objBean.setUserId(rs.getInt("User_ID"));
                    objBean.setUsername(rs.getString("Username"));
                    objBean.setPassword(rs.getString("Password"));
                    objBean.setUserType(rs.getString("User_Type"));
                    objBean.setUserStatus(rs.getString("User_Status"));
                    objBean.setEmail(rs.getString("Email"));
                    objBean.setName(rs.getString("Name"));
                    objBean.setContactNumber(rs.getString("Contact_Number"));
                }
            }
        } catch (Exception e) {
            System.out.println("getUserDetailByUserId(int userId) of DBoperations : " + e);
        } finally {
            try {
                rs.close();
                pstmt.close();
                conn.close();
            } catch (Exception e) {
                System.out.println("getUserDetailByUserId(int userId) of DBoperations : " + e);
            }
        }
        return objBean;
    }
/**
     * This method is used to get Maximum UserId and will return variable of Integer type 
     * @return Integer
     */
    public int getMaxUserId() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int maxUserID = 0;
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement("select max(User_ID) from usermaster");
            rs = pstmt.executeQuery();

            if (rs.next()) {
                maxUserID = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("getMaxUserId() of DBoperations : " + e);
        } finally {
            try {
                rs.close();
                pstmt.close();
                conn.close();
            } catch (Exception e) {
                System.out.println("getMaxUserId() of DBoperations : " + e);
            }
        }
        return maxUserID;
    }
/**
     * This method is used to insert new User detail in database and will return variable of type String
     * @param objBean
     * @return String
     */
    public String addUserAccountDetail(UsermasterBean objBean) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String result = "failed";
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement("select * from usermaster where Username = ?");
            pstmt.setString(1, objBean.getUsername());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                result = "exists";
            } else {

                pstmt = conn.prepareStatement("insert into usermaster ( User_ID ,Username ,Password,User_Type, User_Status ,Name , Email , Contact_Number) values(?,?,?,?,?,?,?,?) ");
                pstmt.setInt(1, objBean.getUserId());
                pstmt.setString(2, objBean.getUsername());
                pstmt.setString(3, objBean.getPassword());
                pstmt.setString(4, objBean.getUserType());
                pstmt.setString(5, objBean.getUserStatus());
                pstmt.setString(6, objBean.getName());
                pstmt.setString(7, objBean.getEmail());
                pstmt.setString(8, objBean.getContactNumber());

                System.out.println(pstmt.toString());
                int i = pstmt.executeUpdate();
                if (i > 0) {
                    result = "added";
                }
            }
        } catch (Exception e) {
            System.out.println("addUserAccountDetail(UsermasterBean objBean) of DBoperations : " + e);
        } finally {
            try {
                rs.close();
                pstmt.close();
                conn.close();
            } catch (Exception e) {
                System.out.println("addUserAccountDetail(UsermasterBean objBean) of DBoperations : " + e);
            }
        }
        return result;
    }
/**
     * This method is used to update User's Detail and will return variable of type String
     * @param objBean
     * @return String
     */
    public String updateUserAccountDetail(UsermasterBean objBean) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String result = "failed";
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement("select * from usermaster where Username = ? and User_ID !=?");
            pstmt.setString(1, objBean.getUsername());
            pstmt.setInt(2, objBean.getUserId());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                result = "exists";
            } else {
                pstmt = conn.prepareStatement("update usermaster set Username = ?,Password=?,User_Type =?, User_Status = ? ,Name = ?, Email = ?, Contact_Number = ? where User_ID=?");
                pstmt.setString(1, objBean.getUsername());
                pstmt.setString(2, objBean.getPassword());
                pstmt.setString(3, objBean.getUserType());
                pstmt.setString(4, objBean.getUserStatus());
                pstmt.setString(5, objBean.getName());
                pstmt.setString(6, objBean.getEmail());
                pstmt.setString(7, objBean.getContactNumber());
                pstmt.setInt(8, objBean.getUserId());
                System.out.println(pstmt.toString());
                int i = pstmt.executeUpdate();
                if (i > 0) {
                    result = "updated";
                }
            }
        } catch (Exception e) {
            System.out.println("updateUserAccountDetail(UsermasterBean objBean) of DBoperations : " + e);
        } finally {
            try {
                rs.close();
                pstmt.close();
                conn.close();
            } catch (Exception e) {
                System.out.println("updateUserAccountDetail(UsermasterBean objBean) of DBoperations : " + e);
            }
        }
        return result;
    }
/**
     * This method is used to get user Id corresponding to Username
     * @param username
     * @return Integer
     */
    public int getUserId(String username) {

        Connection conn = null;
        Statement stmt = null;

        ResultSet rs = null;
        int userID = 1;


        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select User_ID from usermaster where UserName ='" + username + "' ");
            while (rs.next()) {
                userID = rs.getInt("User_ID");
            }
        } catch (Exception e) {
            System.out.println("in dboperations  getUserId() : " + e);
            return userID;
        } finally {
            try {
                rs.close();
                stmt.close();
                conn.close();
            } catch (Exception e) {
                System.out.println("in dboperations getUserId() finally" + e);
                return userID;
            }
        }
        return userID;
    }
/**
     * This method is used to get List of Usernames of All Users
     * @return ArrayList
     */
      public ArrayList getUsernameList() {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList al = new ArrayList();
        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select UserName from usermaster");

            while (rs.next()) {
                al.add(rs.getString("UserName"));
            }
        } catch (Exception e) {
            System.out.println("in dboperations getUsernameList()" + e);
            return al;
        } finally {
            try {
                rs.close();
                stmt.close();
                conn.close();
            } catch (Exception e) {
                System.out.println("in dboperations getUsernameList() finally" + e);
                return al;
            }
        }

        return al;
    }

    //---------------------------------------------------------------------------------------
    //          Common Methods
    //-----------------------------------------------------------------------------------------------
   /**
       * This method returns Current Date and Time Value of System as a String 
       * @return String
       */
      public String getCurrentDateTime() {
        java.util.Date dd = new java.util.Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

        String strDate = sdf.format(dd);
        return strDate;
    }
}
