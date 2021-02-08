package com.komenta.be.model.follow;

public class FollowerRankingDTO {

    private int ranking;
    private int u_id;
    private String u_nickname;
    private String u_profile_pic;
    private int follower_count;

    public FollowerRankingDTO(){}

    public FollowerRankingDTO(int ranking, int u_id, String u_nickname, String u_profile_pic, int follower_count) {
        this.ranking = ranking;
        this.u_id = u_id;
        this.u_nickname = u_nickname;
        this.u_profile_pic = u_profile_pic;
        this.follower_count = follower_count;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public String getU_nickname() {
        return u_nickname;
    }

    public void setU_nickname(String u_nickname) {
        this.u_nickname = u_nickname;
    }

    public String getU_profile_pic() {
        return u_profile_pic;
    }

    public void setU_profile_pic(String u_profile_pic) {
        this.u_profile_pic = u_profile_pic;
    }

    public int getFollower_count() {
        return follower_count;
    }

    public void setFollower_count(int follower_count) {
        this.follower_count = follower_count;
    }

    @Override
    public String toString() {
        return "FollowerRankingDTO{" +
                "ranking=" + ranking +
                ", u_id=" + u_id +
                ", u_nickname='" + u_nickname + '\'' +
                ", u_profile_pic='" + u_profile_pic + '\'' +
                ", follower_count=" + follower_count +
                '}';
    }
}
