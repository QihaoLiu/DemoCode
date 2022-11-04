package com.lqh.demo.bean;

import java.util.List;

public class PlayList {

    private List<Playlists> playlists;
    private Integer total;
    private Integer code;
    private Boolean more;
    private String cat;

    public List<Playlists> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlists> playlists) {
        this.playlists = playlists;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Boolean getOre() {
        return more;
    }

    public void setOre(Boolean ore) {
        more = ore;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public static class Playlists {
        private String name;
        private Long id;
        private String coverImgUrl;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getCoverImgUrl() {
            return coverImgUrl;
        }

        public void setCoverImgUrl(String coverImgUrl) {
            this.coverImgUrl = coverImgUrl;
        }
    }
}
