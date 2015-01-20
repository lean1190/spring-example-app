package ar.edu.unlp.info.dto;

import ar.edu.unlp.info.model.User;

public class UserDTO {

    private long id;
    private String name, username, password;
    private Double money;
    private Integer successPoints;
    private Double totalInvested;
    private Boolean administrator;

    public UserDTO() {
    }

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.money = user.getMoney();
        this.successPoints = user.getSuccessPoints();
        this.totalInvested = user.getTotalInvestedAmount();
        this.administrator = user.isAdministrator();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getSuccessPoints() {
        return successPoints;
    }

    public void setSuccessPoints(Integer successPoints) {
        this.successPoints = successPoints;
    }

    public Double getTotalInvested() {
        return totalInvested;
    }

    public void setTotalInvested(Double totalInvested) {
        this.totalInvested = totalInvested;
    }

    public Boolean isAdministrator() {
        return administrator;
    }

    public void setAdministrator(Boolean administrator) {
        this.administrator = administrator;
    }

}
