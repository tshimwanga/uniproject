package db;

import java.sql.Date;

public class TacheData {
    private String name;
    private String description;
    private Date deadline;
    private String time;
    private String done;
    private String folder;
    
    
    
    public TacheData(String Name, String Description, Date DeadLine, String Time, String Done, String Folder)
    {
    	this.name = Name;
    	this.description = Description;
    	this.deadline = DeadLine;
    	this.time = Time;
    	this.done = Done;
    	this.folder = Folder;
    	
    	
    	
    }
    
    public String getName() {
    	
    	return name;
    }
    
    public String getDescription() {
    	
    	return description;
    }
    
    public Date getDeadline() {
        return deadline;
    }

    public String getTime() {
    	
    	return time;
    }
    
    public String getDone() {
    	
    	return done;
    }
    public String getFolder() {
    	
    	return folder;
    }
    
    
}
