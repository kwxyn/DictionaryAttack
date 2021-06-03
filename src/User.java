
public class User {
	private String name;

    private String hash;
    private String password;
    
    public User(String name, String hash)
    {
    	this.name = name;
    	this.hash = hash;
        this.password = "Not Found in your dictionary";
    }
    
    public String getName() 
    {
    	return this.name;
    }
    
    public String getHash()
    {
    	return this.hash;
    }

    public String getPassword()
    {
        return this.password;
    }

    public void setPassword(String word)
    {
        this.password = word;
    }

}
