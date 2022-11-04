package com.lqh.demo.bean;

import java.util.List;

public class PlayListDetails {

    private Integer code;
    private Object relatedVideos;
    private Playlist playlist;
    private Object urls;
    private Object sharedPrivilege;
    private Object resEntrance;
    private Object fromUsers;
    private Integer fromUserCount;
    private Object songFromUsers;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getRelatedVideos() {
        return relatedVideos;
    }

    public void setRelatedVideos(Object relatedVideos) {
        this.relatedVideos = relatedVideos;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    public Object getUrls() {
        return urls;
    }

    public void setUrls(Object urls) {
        this.urls = urls;
    }

    public Object getSharedPrivilege() {
        return sharedPrivilege;
    }

    public void setSharedPrivilege(Object sharedPrivilege) {
        this.sharedPrivilege = sharedPrivilege;
    }

    public Object getResEntrance() {
        return resEntrance;
    }

    public void setResEntrance(Object resEntrance) {
        this.resEntrance = resEntrance;
    }

    public Object getFromUsers() {
        return fromUsers;
    }

    public void setFromUsers(Object fromUsers) {
        this.fromUsers = fromUsers;
    }

    public Integer getFromUserCount() {
        return fromUserCount;
    }

    public void setFromUserCount(Integer fromUserCount) {
        this.fromUserCount = fromUserCount;
    }

    public Object getSongFromUsers() {
        return songFromUsers;
    }

    public void setSongFromUsers(Object songFromUsers) {
        this.songFromUsers = songFromUsers;
    }

    public static class Playlist {
        private Long id;
        private String name;
        private Long coverImgId;
        private String coverImgUrl;
        private String coverimgidStr;
        private Integer adType;
        private Integer userId;
        private Long createTime;
        private Integer status;
        private Boolean opRecommend;
        private Boolean highQuality;
        private Boolean newImported;
        private Integer updateTime;
        private Integer trackCount;
        private Integer specialType;
        private Integer privacy;
        private Long trackUpdateTime;
        private String commentThreadId;
        private Integer playCount;
        private Long trackNumberUpdateTime;
        private Integer subscribedCount;
        private Integer cloudTrackCount;
        private Boolean ordered;
        private String description;
        private List<String> tags;
        private String updateFrequency;
        private Long backgroundCoverId;
        private String backgroundCoverUrl;
        private Long titleImage;
        private String titleImageUrl;
        private String englishTitle;
        private String officialPlaylistType;
        private Boolean copied;
        private List<?> subscribers;
        private Object subscribed;
        private Creator creator;
        private List<Tracks> tracks;
        private Object videoIds;
        private Object videos;
        private Object bannedTrackIds;
        private Integer shareCount;
        private Integer commentCount;
        private Object remixVideo;
        private Object sharedUsers;
        private Object historySharedUsers;
        private String gradeStatus;
        private Object score;
        private Object algTags;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Long getCoverImgId() {
            return coverImgId;
        }

        public void setCoverImgId(Long coverImgId) {
            this.coverImgId = coverImgId;
        }

        public String getCoverImgUrl() {
            return coverImgUrl;
        }

        public void setCoverImgUrl(String coverImgUrl) {
            this.coverImgUrl = coverImgUrl;
        }

        public String getCoverimgidStr() {
            return coverimgidStr;
        }

        public void setCoverimgidStr(String coverimgidStr) {
            this.coverimgidStr = coverimgidStr;
        }

        public Integer getAdType() {
            return adType;
        }

        public void setAdType(Integer adType) {
            this.adType = adType;
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public Long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Long createTime) {
            this.createTime = createTime;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public Boolean getOpRecommend() {
            return opRecommend;
        }

        public void setOpRecommend(Boolean opRecommend) {
            this.opRecommend = opRecommend;
        }

        public Boolean getHighQuality() {
            return highQuality;
        }

        public void setHighQuality(Boolean highQuality) {
            this.highQuality = highQuality;
        }

        public Boolean getNewImported() {
            return newImported;
        }

        public void setNewImported(Boolean newImported) {
            this.newImported = newImported;
        }

        public Integer getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Integer updateTime) {
            this.updateTime = updateTime;
        }

        public Integer getTrackCount() {
            return trackCount;
        }

        public void setTrackCount(Integer trackCount) {
            this.trackCount = trackCount;
        }

        public Integer getSpecialType() {
            return specialType;
        }

        public void setSpecialType(Integer specialType) {
            this.specialType = specialType;
        }

        public Integer getPrivacy() {
            return privacy;
        }

        public void setPrivacy(Integer privacy) {
            this.privacy = privacy;
        }

        public Long getTrackUpdateTime() {
            return trackUpdateTime;
        }

        public void setTrackUpdateTime(Long trackUpdateTime) {
            this.trackUpdateTime = trackUpdateTime;
        }

        public String getCommentThreadId() {
            return commentThreadId;
        }

        public void setCommentThreadId(String commentThreadId) {
            this.commentThreadId = commentThreadId;
        }

        public Integer getPlayCount() {
            return playCount;
        }

        public void setPlayCount(Integer playCount) {
            this.playCount = playCount;
        }

        public Long getTrackNumberUpdateTime() {
            return trackNumberUpdateTime;
        }

        public void setTrackNumberUpdateTime(Long trackNumberUpdateTime) {
            this.trackNumberUpdateTime = trackNumberUpdateTime;
        }

        public Integer getSubscribedCount() {
            return subscribedCount;
        }

        public void setSubscribedCount(Integer subscribedCount) {
            this.subscribedCount = subscribedCount;
        }

        public Integer getCloudTrackCount() {
            return cloudTrackCount;
        }

        public void setCloudTrackCount(Integer cloudTrackCount) {
            this.cloudTrackCount = cloudTrackCount;
        }

        public Boolean getOrdered() {
            return ordered;
        }

        public void setOrdered(Boolean ordered) {
            this.ordered = ordered;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public List<String> getTags() {
            return tags;
        }

        public void setTags(List<String> tags) {
            this.tags = tags;
        }

        public String getUpdateFrequency() {
            return updateFrequency;
        }

        public void setUpdateFrequency(String updateFrequency) {
            this.updateFrequency = updateFrequency;
        }

        public Long getBackgroundCoverId() {
            return backgroundCoverId;
        }

        public void setBackgroundCoverId(Long backgroundCoverId) {
            this.backgroundCoverId = backgroundCoverId;
        }

        public String getBackgroundCoverUrl() {
            return backgroundCoverUrl;
        }

        public void setBackgroundCoverUrl(String backgroundCoverUrl) {
            this.backgroundCoverUrl = backgroundCoverUrl;
        }

        public Long getTitleImage() {
            return titleImage;
        }

        public void setTitleImage(Long titleImage) {
            this.titleImage = titleImage;
        }

        public String getTitleImageUrl() {
            return titleImageUrl;
        }

        public void setTitleImageUrl(String titleImageUrl) {
            this.titleImageUrl = titleImageUrl;
        }

        public String getEnglishTitle() {
            return englishTitle;
        }

        public void setEnglishTitle(String englishTitle) {
            this.englishTitle = englishTitle;
        }

        public String getOfficialPlaylistType() {
            return officialPlaylistType;
        }

        public void setOfficialPlaylistType(String officialPlaylistType) {
            this.officialPlaylistType = officialPlaylistType;
        }

        public Boolean getCopied() {
            return copied;
        }

        public void setCopied(Boolean copied) {
            this.copied = copied;
        }

        public List<?> getSubscribers() {
            return subscribers;
        }

        public void setSubscribers(List<?> subscribers) {
            this.subscribers = subscribers;
        }

        public Object getSubscribed() {
            return subscribed;
        }

        public void setSubscribed(Object subscribed) {
            this.subscribed = subscribed;
        }

        public Creator getCreator() {
            return creator;
        }

        public void setCreator(Creator creator) {
            this.creator = creator;
        }

        public List<Tracks> getTracks() {
            return tracks;
        }

        public void setTracks(List<Tracks> tracks) {
            this.tracks = tracks;
        }

        public Object getVideoIds() {
            return videoIds;
        }

        public void setVideoIds(Object videoIds) {
            this.videoIds = videoIds;
        }

        public Object getVideos() {
            return videos;
        }

        public void setVideos(Object videos) {
            this.videos = videos;
        }

        public Object getBannedTrackIds() {
            return bannedTrackIds;
        }

        public void setBannedTrackIds(Object bannedTrackIds) {
            this.bannedTrackIds = bannedTrackIds;
        }

        public Integer getShareCount() {
            return shareCount;
        }

        public void setShareCount(Integer shareCount) {
            this.shareCount = shareCount;
        }

        public Integer getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(Integer commentCount) {
            this.commentCount = commentCount;
        }

        public Object getRemixVideo() {
            return remixVideo;
        }

        public void setRemixVideo(Object remixVideo) {
            this.remixVideo = remixVideo;
        }

        public Object getSharedUsers() {
            return sharedUsers;
        }

        public void setSharedUsers(Object sharedUsers) {
            this.sharedUsers = sharedUsers;
        }

        public Object getHistorySharedUsers() {
            return historySharedUsers;
        }

        public void setHistorySharedUsers(Object historySharedUsers) {
            this.historySharedUsers = historySharedUsers;
        }

        public String getGradeStatus() {
            return gradeStatus;
        }

        public void setGradeStatus(String gradeStatus) {
            this.gradeStatus = gradeStatus;
        }

        public Object getScore() {
            return score;
        }

        public void setScore(Object score) {
            this.score = score;
        }

        public Object getAlgTags() {
            return algTags;
        }

        public void setAlgTags(Object algTags) {
            this.algTags = algTags;
        }

        public static class Creator {
            private Boolean defaultAvatar;
            private Integer province;
            private Integer authStatus;
            private Boolean followed;
            private String avatarUrl;
            private Integer accountStatus;
            private Integer gender;
            private Integer city;
            private Integer birthday;
            private Integer userId;
            private Integer userType;
            private String nickname;
            private String signature;
            private String description;
            private String detailDescription;
            private Long avatarImgId;
            private Long backgroundImgId;
            private String backgroundUrl;
            private Integer authority;
            private Boolean mutual;
            private Object expertTags;
            private Object experts;
            private Integer djStatus;
            private Integer vipType;
            private Object remarkName;
            private Integer authenticationTypes;
            private AvatarDetail avatarDetail;
            private String avatarImgIdStr;
            private String backgroundImgIdStr;
            private Boolean anchor;
            private String avatarimgidStr;

            public Boolean getDefaultAvatar() {
                return defaultAvatar;
            }

            public void setDefaultAvatar(Boolean defaultAvatar) {
                this.defaultAvatar = defaultAvatar;
            }

            public Integer getProvince() {
                return province;
            }

            public void setProvince(Integer province) {
                this.province = province;
            }

            public Integer getAuthStatus() {
                return authStatus;
            }

            public void setAuthStatus(Integer authStatus) {
                this.authStatus = authStatus;
            }

            public Boolean getFollowed() {
                return followed;
            }

            public void setFollowed(Boolean followed) {
                this.followed = followed;
            }

            public String getAvatarUrl() {
                return avatarUrl;
            }

            public void setAvatarUrl(String avatarUrl) {
                this.avatarUrl = avatarUrl;
            }

            public Integer getAccountStatus() {
                return accountStatus;
            }

            public void setAccountStatus(Integer accountStatus) {
                this.accountStatus = accountStatus;
            }

            public Integer getGender() {
                return gender;
            }

            public void setGender(Integer gender) {
                this.gender = gender;
            }

            public Integer getCity() {
                return city;
            }

            public void setCity(Integer city) {
                this.city = city;
            }

            public Integer getBirthday() {
                return birthday;
            }

            public void setBirthday(Integer birthday) {
                this.birthday = birthday;
            }

            public Integer getUserId() {
                return userId;
            }

            public void setUserId(Integer userId) {
                this.userId = userId;
            }

            public Integer getUserType() {
                return userType;
            }

            public void setUserType(Integer userType) {
                this.userType = userType;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getSignature() {
                return signature;
            }

            public void setSignature(String signature) {
                this.signature = signature;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getDetailDescription() {
                return detailDescription;
            }

            public void setDetailDescription(String detailDescription) {
                this.detailDescription = detailDescription;
            }

            public Long getAvatarImgId() {
                return avatarImgId;
            }

            public void setAvatarImgId(Long avatarImgId) {
                this.avatarImgId = avatarImgId;
            }

            public Long getBackgroundImgId() {
                return backgroundImgId;
            }

            public void setBackgroundImgId(Long backgroundImgId) {
                this.backgroundImgId = backgroundImgId;
            }

            public String getBackgroundUrl() {
                return backgroundUrl;
            }

            public void setBackgroundUrl(String backgroundUrl) {
                this.backgroundUrl = backgroundUrl;
            }

            public Integer getAuthority() {
                return authority;
            }

            public void setAuthority(Integer authority) {
                this.authority = authority;
            }

            public Boolean getUtual() {
                return mutual;
            }

            public void setUtual(Boolean utual) {
                mutual = utual;
            }

            public Object getExpertTags() {
                return expertTags;
            }

            public void setExpertTags(Object expertTags) {
                this.expertTags = expertTags;
            }

            public Object getExperts() {
                return experts;
            }

            public void setExperts(Object experts) {
                this.experts = experts;
            }

            public Integer getDjStatus() {
                return djStatus;
            }

            public void setDjStatus(Integer djStatus) {
                this.djStatus = djStatus;
            }

            public Integer getVipType() {
                return vipType;
            }

            public void setVipType(Integer vipType) {
                this.vipType = vipType;
            }

            public Object getRemarkName() {
                return remarkName;
            }

            public void setRemarkName(Object remarkName) {
                this.remarkName = remarkName;
            }

            public Integer getAuthenticationTypes() {
                return authenticationTypes;
            }

            public void setAuthenticationTypes(Integer authenticationTypes) {
                this.authenticationTypes = authenticationTypes;
            }

            public AvatarDetail getAvatarDetail() {
                return avatarDetail;
            }

            public void setAvatarDetail(AvatarDetail avatarDetail) {
                this.avatarDetail = avatarDetail;
            }

            public String getAvatarImgIdStr() {
                return avatarImgIdStr;
            }

            public void setAvatarImgIdStr(String avatarImgIdStr) {
                this.avatarImgIdStr = avatarImgIdStr;
            }

            public String getBackgroundImgIdStr() {
                return backgroundImgIdStr;
            }

            public void setBackgroundImgIdStr(String backgroundImgIdStr) {
                this.backgroundImgIdStr = backgroundImgIdStr;
            }

            public Boolean getAnchor() {
                return anchor;
            }

            public void setAnchor(Boolean anchor) {
                this.anchor = anchor;
            }

            public String getAvatarimgidStr() {
                return avatarimgidStr;
            }

            public void setAvatarimgidStr(String avatarimgidStr) {
                this.avatarimgidStr = avatarimgidStr;
            }

            public static class AvatarDetail {
                private Integer userType;
                private Integer identityLevel;
                private String identityIconUrl;

                public Integer getUserType() {
                    return userType;
                }

                public void setUserType(Integer userType) {
                    this.userType = userType;
                }

                public Integer getIdentityLevel() {
                    return identityLevel;
                }

                public void setIdentityLevel(Integer identityLevel) {
                    this.identityLevel = identityLevel;
                }

                public String getIdentityIconUrl() {
                    return identityIconUrl;
                }

                public void setIdentityIconUrl(String identityIconUrl) {
                    this.identityIconUrl = identityIconUrl;
                }
            }
        }

        public static class Tracks {
        }
    }
}
