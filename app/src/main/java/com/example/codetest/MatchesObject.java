package com.example.codetest;

public class MatchesObject{
    private String userId;
    private String firstName;
    private String lastName;
    private String profileImageUrl;

    public MatchesObject(String userId, String firstName, String lastName, String profileImageUrl)
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

    public String getProfileImageUrl()
    {
        return profileImageUrl;
    }
    public void setProfileImageUrl(String profileImageUrl)
    {
        this.profileImageUrl = profileImageUrl;
    }

}
