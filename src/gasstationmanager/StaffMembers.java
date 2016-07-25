/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gasstationmanager;

/**
 *
 * @author Joe Mwega
 */
public class StaffMembers {
    private String staffFirstName;
    private String staffLastName;
    private String staffWorkID;
    private String staffPosition;
    private String staffUserName;
    private char[] staffPassWord;
    
    
    public StaffMembers(String firstname, String lastname, String workId, String position, String username, char[] password ) {
        staffFirstName = firstname;
        staffLastName = lastname;
        staffWorkID = workId;
        staffUserName = username;
        staffPassWord = password;
        staffPosition = position;
    }
    
    public String getFirstName() {
        return staffFirstName;
    }
    
    public void setFirstName(String staffFirstName) {
        this.staffFirstName = staffFirstName;
    }
    
    public String getLastName() {
        return staffLastName;
    }
    
    public void setLastName(String staffLastName) {
        this.staffLastName = staffLastName;
    }
    
    public String getWorkID() {
        return staffWorkID;
    }
    
    public void setWorkId(String staffWorkID) {
        this.staffWorkID = staffWorkID;
    }
    
    public String getStaffPosition() {
        return staffPosition;
        
    }
    
    public void setStaffPosition() {
        this.staffPosition = staffPosition;
    }
    
    public String getStaffUserName() {
        return staffUserName;
    }
    
    public void setStaffUserName(String staffUserName) {
        this.staffUserName = staffUserName;
    }
    
    public char[] getStaffPassWord() {
        return staffPassWord;
    }
    
    public void setStaffPassWord(char[] staffPassWord) {
        this.staffPassWord = staffPassWord;
    }
    
    
    
    
}
