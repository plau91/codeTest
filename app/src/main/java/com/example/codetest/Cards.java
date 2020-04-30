package com.example.codetest;

public class Cards {
    private String userId;
    private String firstName;
    private String lastName;
    private String profileImageUrl;
    public Cards(String userId, String firstName, String lastName, String profileImageUrl)
    {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profileImageUrl = profileImageUrl;
    }

    public String getUserId()
    {
        return userId;
    }
    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getFirstName()
    {
        return firstName;
    }
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getprofileImageUrl()
    {
        return profileImageUrl;
    }
    public void setprofileImageUrl(String profileImageUrl)
    {
        this.profileImageUrl = profileImageUrl;
    }

}
