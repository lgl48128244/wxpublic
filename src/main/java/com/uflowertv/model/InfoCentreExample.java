package com.uflowertv.model;

import java.util.ArrayList;
import java.util.List;

public class InfoCentreExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public InfoCentreExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andWxUserIdIsNull() {
            addCriterion("wx_user_id is null");
            return (Criteria) this;
        }

        public Criteria andWxUserIdIsNotNull() {
            addCriterion("wx_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andWxUserIdEqualTo(String value) {
            addCriterion("wx_user_id =", value, "wxUserId");
            return (Criteria) this;
        }

        public Criteria andWxUserIdNotEqualTo(String value) {
            addCriterion("wx_user_id <>", value, "wxUserId");
            return (Criteria) this;
        }

        public Criteria andWxUserIdGreaterThan(String value) {
            addCriterion("wx_user_id >", value, "wxUserId");
            return (Criteria) this;
        }

        public Criteria andWxUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("wx_user_id >=", value, "wxUserId");
            return (Criteria) this;
        }

        public Criteria andWxUserIdLessThan(String value) {
            addCriterion("wx_user_id <", value, "wxUserId");
            return (Criteria) this;
        }

        public Criteria andWxUserIdLessThanOrEqualTo(String value) {
            addCriterion("wx_user_id <=", value, "wxUserId");
            return (Criteria) this;
        }

        public Criteria andWxUserIdLike(String value) {
            addCriterion("wx_user_id like", value, "wxUserId");
            return (Criteria) this;
        }

        public Criteria andWxUserIdNotLike(String value) {
            addCriterion("wx_user_id not like", value, "wxUserId");
            return (Criteria) this;
        }

        public Criteria andWxUserIdIn(List<String> values) {
            addCriterion("wx_user_id in", values, "wxUserId");
            return (Criteria) this;
        }

        public Criteria andWxUserIdNotIn(List<String> values) {
            addCriterion("wx_user_id not in", values, "wxUserId");
            return (Criteria) this;
        }

        public Criteria andWxUserIdBetween(String value1, String value2) {
            addCriterion("wx_user_id between", value1, value2, "wxUserId");
            return (Criteria) this;
        }

        public Criteria andWxUserIdNotBetween(String value1, String value2) {
            addCriterion("wx_user_id not between", value1, value2, "wxUserId");
            return (Criteria) this;
        }

        public Criteria andWxUserPhoneIsNull() {
            addCriterion("wx_user_phone is null");
            return (Criteria) this;
        }

        public Criteria andWxUserPhoneIsNotNull() {
            addCriterion("wx_user_phone is not null");
            return (Criteria) this;
        }

        public Criteria andWxUserPhoneEqualTo(String value) {
            addCriterion("wx_user_phone =", value, "wxUserPhone");
            return (Criteria) this;
        }

        public Criteria andWxUserPhoneNotEqualTo(String value) {
            addCriterion("wx_user_phone <>", value, "wxUserPhone");
            return (Criteria) this;
        }

        public Criteria andWxUserPhoneGreaterThan(String value) {
            addCriterion("wx_user_phone >", value, "wxUserPhone");
            return (Criteria) this;
        }

        public Criteria andWxUserPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("wx_user_phone >=", value, "wxUserPhone");
            return (Criteria) this;
        }

        public Criteria andWxUserPhoneLessThan(String value) {
            addCriterion("wx_user_phone <", value, "wxUserPhone");
            return (Criteria) this;
        }

        public Criteria andWxUserPhoneLessThanOrEqualTo(String value) {
            addCriterion("wx_user_phone <=", value, "wxUserPhone");
            return (Criteria) this;
        }

        public Criteria andWxUserPhoneLike(String value) {
            addCriterion("wx_user_phone like", value, "wxUserPhone");
            return (Criteria) this;
        }

        public Criteria andWxUserPhoneNotLike(String value) {
            addCriterion("wx_user_phone not like", value, "wxUserPhone");
            return (Criteria) this;
        }

        public Criteria andWxUserPhoneIn(List<String> values) {
            addCriterion("wx_user_phone in", values, "wxUserPhone");
            return (Criteria) this;
        }

        public Criteria andWxUserPhoneNotIn(List<String> values) {
            addCriterion("wx_user_phone not in", values, "wxUserPhone");
            return (Criteria) this;
        }

        public Criteria andWxUserPhoneBetween(String value1, String value2) {
            addCriterion("wx_user_phone between", value1, value2, "wxUserPhone");
            return (Criteria) this;
        }

        public Criteria andWxUserPhoneNotBetween(String value1, String value2) {
            addCriterion("wx_user_phone not between", value1, value2, "wxUserPhone");
            return (Criteria) this;
        }

        public Criteria andWxUserPicurlIsNull() {
            addCriterion("wx_user_picurl is null");
            return (Criteria) this;
        }

        public Criteria andWxUserPicurlIsNotNull() {
            addCriterion("wx_user_picurl is not null");
            return (Criteria) this;
        }

        public Criteria andWxUserPicurlEqualTo(String value) {
            addCriterion("wx_user_picurl =", value, "wxUserPicurl");
            return (Criteria) this;
        }

        public Criteria andWxUserPicurlNotEqualTo(String value) {
            addCriterion("wx_user_picurl <>", value, "wxUserPicurl");
            return (Criteria) this;
        }

        public Criteria andWxUserPicurlGreaterThan(String value) {
            addCriterion("wx_user_picurl >", value, "wxUserPicurl");
            return (Criteria) this;
        }

        public Criteria andWxUserPicurlGreaterThanOrEqualTo(String value) {
            addCriterion("wx_user_picurl >=", value, "wxUserPicurl");
            return (Criteria) this;
        }

        public Criteria andWxUserPicurlLessThan(String value) {
            addCriterion("wx_user_picurl <", value, "wxUserPicurl");
            return (Criteria) this;
        }

        public Criteria andWxUserPicurlLessThanOrEqualTo(String value) {
            addCriterion("wx_user_picurl <=", value, "wxUserPicurl");
            return (Criteria) this;
        }

        public Criteria andWxUserPicurlLike(String value) {
            addCriterion("wx_user_picurl like", value, "wxUserPicurl");
            return (Criteria) this;
        }

        public Criteria andWxUserPicurlNotLike(String value) {
            addCriterion("wx_user_picurl not like", value, "wxUserPicurl");
            return (Criteria) this;
        }

        public Criteria andWxUserPicurlIn(List<String> values) {
            addCriterion("wx_user_picurl in", values, "wxUserPicurl");
            return (Criteria) this;
        }

        public Criteria andWxUserPicurlNotIn(List<String> values) {
            addCriterion("wx_user_picurl not in", values, "wxUserPicurl");
            return (Criteria) this;
        }

        public Criteria andWxUserPicurlBetween(String value1, String value2) {
            addCriterion("wx_user_picurl between", value1, value2, "wxUserPicurl");
            return (Criteria) this;
        }

        public Criteria andWxUserPicurlNotBetween(String value1, String value2) {
            addCriterion("wx_user_picurl not between", value1, value2, "wxUserPicurl");
            return (Criteria) this;
        }

        public Criteria andWxUserMsgidIsNull() {
            addCriterion("wx_user_msgid is null");
            return (Criteria) this;
        }

        public Criteria andWxUserMsgidIsNotNull() {
            addCriterion("wx_user_msgid is not null");
            return (Criteria) this;
        }

        public Criteria andWxUserMsgidEqualTo(String value) {
            addCriterion("wx_user_msgid =", value, "wxUserMsgid");
            return (Criteria) this;
        }

        public Criteria andWxUserMsgidNotEqualTo(String value) {
            addCriterion("wx_user_msgid <>", value, "wxUserMsgid");
            return (Criteria) this;
        }

        public Criteria andWxUserMsgidGreaterThan(String value) {
            addCriterion("wx_user_msgid >", value, "wxUserMsgid");
            return (Criteria) this;
        }

        public Criteria andWxUserMsgidGreaterThanOrEqualTo(String value) {
            addCriterion("wx_user_msgid >=", value, "wxUserMsgid");
            return (Criteria) this;
        }

        public Criteria andWxUserMsgidLessThan(String value) {
            addCriterion("wx_user_msgid <", value, "wxUserMsgid");
            return (Criteria) this;
        }

        public Criteria andWxUserMsgidLessThanOrEqualTo(String value) {
            addCriterion("wx_user_msgid <=", value, "wxUserMsgid");
            return (Criteria) this;
        }

        public Criteria andWxUserMsgidLike(String value) {
            addCriterion("wx_user_msgid like", value, "wxUserMsgid");
            return (Criteria) this;
        }

        public Criteria andWxUserMsgidNotLike(String value) {
            addCriterion("wx_user_msgid not like", value, "wxUserMsgid");
            return (Criteria) this;
        }

        public Criteria andWxUserMsgidIn(List<String> values) {
            addCriterion("wx_user_msgid in", values, "wxUserMsgid");
            return (Criteria) this;
        }

        public Criteria andWxUserMsgidNotIn(List<String> values) {
            addCriterion("wx_user_msgid not in", values, "wxUserMsgid");
            return (Criteria) this;
        }

        public Criteria andWxUserMsgidBetween(String value1, String value2) {
            addCriterion("wx_user_msgid between", value1, value2, "wxUserMsgid");
            return (Criteria) this;
        }

        public Criteria andWxUserMsgidNotBetween(String value1, String value2) {
            addCriterion("wx_user_msgid not between", value1, value2, "wxUserMsgid");
            return (Criteria) this;
        }

        public Criteria andWxUserMediaidIsNull() {
            addCriterion("wx_user_mediaid is null");
            return (Criteria) this;
        }

        public Criteria andWxUserMediaidIsNotNull() {
            addCriterion("wx_user_mediaid is not null");
            return (Criteria) this;
        }

        public Criteria andWxUserMediaidEqualTo(String value) {
            addCriterion("wx_user_mediaid =", value, "wxUserMediaid");
            return (Criteria) this;
        }

        public Criteria andWxUserMediaidNotEqualTo(String value) {
            addCriterion("wx_user_mediaid <>", value, "wxUserMediaid");
            return (Criteria) this;
        }

        public Criteria andWxUserMediaidGreaterThan(String value) {
            addCriterion("wx_user_mediaid >", value, "wxUserMediaid");
            return (Criteria) this;
        }

        public Criteria andWxUserMediaidGreaterThanOrEqualTo(String value) {
            addCriterion("wx_user_mediaid >=", value, "wxUserMediaid");
            return (Criteria) this;
        }

        public Criteria andWxUserMediaidLessThan(String value) {
            addCriterion("wx_user_mediaid <", value, "wxUserMediaid");
            return (Criteria) this;
        }

        public Criteria andWxUserMediaidLessThanOrEqualTo(String value) {
            addCriterion("wx_user_mediaid <=", value, "wxUserMediaid");
            return (Criteria) this;
        }

        public Criteria andWxUserMediaidLike(String value) {
            addCriterion("wx_user_mediaid like", value, "wxUserMediaid");
            return (Criteria) this;
        }

        public Criteria andWxUserMediaidNotLike(String value) {
            addCriterion("wx_user_mediaid not like", value, "wxUserMediaid");
            return (Criteria) this;
        }

        public Criteria andWxUserMediaidIn(List<String> values) {
            addCriterion("wx_user_mediaid in", values, "wxUserMediaid");
            return (Criteria) this;
        }

        public Criteria andWxUserMediaidNotIn(List<String> values) {
            addCriterion("wx_user_mediaid not in", values, "wxUserMediaid");
            return (Criteria) this;
        }

        public Criteria andWxUserMediaidBetween(String value1, String value2) {
            addCriterion("wx_user_mediaid between", value1, value2, "wxUserMediaid");
            return (Criteria) this;
        }

        public Criteria andWxUserMediaidNotBetween(String value1, String value2) {
            addCriterion("wx_user_mediaid not between", value1, value2, "wxUserMediaid");
            return (Criteria) this;
        }

        public Criteria andWxPublicIdIsNull() {
            addCriterion("wx_public_id is null");
            return (Criteria) this;
        }

        public Criteria andWxPublicIdIsNotNull() {
            addCriterion("wx_public_id is not null");
            return (Criteria) this;
        }

        public Criteria andWxPublicIdEqualTo(String value) {
            addCriterion("wx_public_id =", value, "wxPublicId");
            return (Criteria) this;
        }

        public Criteria andWxPublicIdNotEqualTo(String value) {
            addCriterion("wx_public_id <>", value, "wxPublicId");
            return (Criteria) this;
        }

        public Criteria andWxPublicIdGreaterThan(String value) {
            addCriterion("wx_public_id >", value, "wxPublicId");
            return (Criteria) this;
        }

        public Criteria andWxPublicIdGreaterThanOrEqualTo(String value) {
            addCriterion("wx_public_id >=", value, "wxPublicId");
            return (Criteria) this;
        }

        public Criteria andWxPublicIdLessThan(String value) {
            addCriterion("wx_public_id <", value, "wxPublicId");
            return (Criteria) this;
        }

        public Criteria andWxPublicIdLessThanOrEqualTo(String value) {
            addCriterion("wx_public_id <=", value, "wxPublicId");
            return (Criteria) this;
        }

        public Criteria andWxPublicIdLike(String value) {
            addCriterion("wx_public_id like", value, "wxPublicId");
            return (Criteria) this;
        }

        public Criteria andWxPublicIdNotLike(String value) {
            addCriterion("wx_public_id not like", value, "wxPublicId");
            return (Criteria) this;
        }

        public Criteria andWxPublicIdIn(List<String> values) {
            addCriterion("wx_public_id in", values, "wxPublicId");
            return (Criteria) this;
        }

        public Criteria andWxPublicIdNotIn(List<String> values) {
            addCriterion("wx_public_id not in", values, "wxPublicId");
            return (Criteria) this;
        }

        public Criteria andWxPublicIdBetween(String value1, String value2) {
            addCriterion("wx_public_id between", value1, value2, "wxPublicId");
            return (Criteria) this;
        }

        public Criteria andWxPublicIdNotBetween(String value1, String value2) {
            addCriterion("wx_public_id not between", value1, value2, "wxPublicId");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createTime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createTime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(String value) {
            addCriterion("createTime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(String value) {
            addCriterion("createTime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(String value) {
            addCriterion("createTime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(String value) {
            addCriterion("createTime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(String value) {
            addCriterion("createTime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(String value) {
            addCriterion("createTime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLike(String value) {
            addCriterion("createTime like", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotLike(String value) {
            addCriterion("createTime not like", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<String> values) {
            addCriterion("createTime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<String> values) {
            addCriterion("createTime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(String value1, String value2) {
            addCriterion("createTime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(String value1, String value2) {
            addCriterion("createTime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andExt1IsNull() {
            addCriterion("ext1 is null");
            return (Criteria) this;
        }

        public Criteria andExt1IsNotNull() {
            addCriterion("ext1 is not null");
            return (Criteria) this;
        }

        public Criteria andExt1EqualTo(String value) {
            addCriterion("ext1 =", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1NotEqualTo(String value) {
            addCriterion("ext1 <>", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1GreaterThan(String value) {
            addCriterion("ext1 >", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1GreaterThanOrEqualTo(String value) {
            addCriterion("ext1 >=", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1LessThan(String value) {
            addCriterion("ext1 <", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1LessThanOrEqualTo(String value) {
            addCriterion("ext1 <=", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1Like(String value) {
            addCriterion("ext1 like", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1NotLike(String value) {
            addCriterion("ext1 not like", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1In(List<String> values) {
            addCriterion("ext1 in", values, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1NotIn(List<String> values) {
            addCriterion("ext1 not in", values, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1Between(String value1, String value2) {
            addCriterion("ext1 between", value1, value2, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1NotBetween(String value1, String value2) {
            addCriterion("ext1 not between", value1, value2, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt2IsNull() {
            addCriterion("ext2 is null");
            return (Criteria) this;
        }

        public Criteria andExt2IsNotNull() {
            addCriterion("ext2 is not null");
            return (Criteria) this;
        }

        public Criteria andExt2EqualTo(String value) {
            addCriterion("ext2 =", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2NotEqualTo(String value) {
            addCriterion("ext2 <>", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2GreaterThan(String value) {
            addCriterion("ext2 >", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2GreaterThanOrEqualTo(String value) {
            addCriterion("ext2 >=", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2LessThan(String value) {
            addCriterion("ext2 <", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2LessThanOrEqualTo(String value) {
            addCriterion("ext2 <=", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2Like(String value) {
            addCriterion("ext2 like", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2NotLike(String value) {
            addCriterion("ext2 not like", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2In(List<String> values) {
            addCriterion("ext2 in", values, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2NotIn(List<String> values) {
            addCriterion("ext2 not in", values, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2Between(String value1, String value2) {
            addCriterion("ext2 between", value1, value2, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2NotBetween(String value1, String value2) {
            addCriterion("ext2 not between", value1, value2, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt3IsNull() {
            addCriterion("ext3 is null");
            return (Criteria) this;
        }

        public Criteria andExt3IsNotNull() {
            addCriterion("ext3 is not null");
            return (Criteria) this;
        }

        public Criteria andExt3EqualTo(String value) {
            addCriterion("ext3 =", value, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3NotEqualTo(String value) {
            addCriterion("ext3 <>", value, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3GreaterThan(String value) {
            addCriterion("ext3 >", value, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3GreaterThanOrEqualTo(String value) {
            addCriterion("ext3 >=", value, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3LessThan(String value) {
            addCriterion("ext3 <", value, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3LessThanOrEqualTo(String value) {
            addCriterion("ext3 <=", value, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3Like(String value) {
            addCriterion("ext3 like", value, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3NotLike(String value) {
            addCriterion("ext3 not like", value, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3In(List<String> values) {
            addCriterion("ext3 in", values, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3NotIn(List<String> values) {
            addCriterion("ext3 not in", values, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3Between(String value1, String value2) {
            addCriterion("ext3 between", value1, value2, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3NotBetween(String value1, String value2) {
            addCriterion("ext3 not between", value1, value2, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt4IsNull() {
            addCriterion("ext4 is null");
            return (Criteria) this;
        }

        public Criteria andExt4IsNotNull() {
            addCriterion("ext4 is not null");
            return (Criteria) this;
        }

        public Criteria andExt4EqualTo(String value) {
            addCriterion("ext4 =", value, "ext4");
            return (Criteria) this;
        }

        public Criteria andExt4NotEqualTo(String value) {
            addCriterion("ext4 <>", value, "ext4");
            return (Criteria) this;
        }

        public Criteria andExt4GreaterThan(String value) {
            addCriterion("ext4 >", value, "ext4");
            return (Criteria) this;
        }

        public Criteria andExt4GreaterThanOrEqualTo(String value) {
            addCriterion("ext4 >=", value, "ext4");
            return (Criteria) this;
        }

        public Criteria andExt4LessThan(String value) {
            addCriterion("ext4 <", value, "ext4");
            return (Criteria) this;
        }

        public Criteria andExt4LessThanOrEqualTo(String value) {
            addCriterion("ext4 <=", value, "ext4");
            return (Criteria) this;
        }

        public Criteria andExt4Like(String value) {
            addCriterion("ext4 like", value, "ext4");
            return (Criteria) this;
        }

        public Criteria andExt4NotLike(String value) {
            addCriterion("ext4 not like", value, "ext4");
            return (Criteria) this;
        }

        public Criteria andExt4In(List<String> values) {
            addCriterion("ext4 in", values, "ext4");
            return (Criteria) this;
        }

        public Criteria andExt4NotIn(List<String> values) {
            addCriterion("ext4 not in", values, "ext4");
            return (Criteria) this;
        }

        public Criteria andExt4Between(String value1, String value2) {
            addCriterion("ext4 between", value1, value2, "ext4");
            return (Criteria) this;
        }

        public Criteria andExt4NotBetween(String value1, String value2) {
            addCriterion("ext4 not between", value1, value2, "ext4");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}