package com.uflowertv.model;

import java.util.ArrayList;
import java.util.List;

public class NoticeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NoticeExample() {
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

        public Criteria andWxUserPicnameIsNull() {
            addCriterion("wx_user_picname is null");
            return (Criteria) this;
        }

        public Criteria andWxUserPicnameIsNotNull() {
            addCriterion("wx_user_picname is not null");
            return (Criteria) this;
        }

        public Criteria andWxUserPicnameEqualTo(String value) {
            addCriterion("wx_user_picname =", value, "wxUserPicname");
            return (Criteria) this;
        }

        public Criteria andWxUserPicnameNotEqualTo(String value) {
            addCriterion("wx_user_picname <>", value, "wxUserPicname");
            return (Criteria) this;
        }

        public Criteria andWxUserPicnameGreaterThan(String value) {
            addCriterion("wx_user_picname >", value, "wxUserPicname");
            return (Criteria) this;
        }

        public Criteria andWxUserPicnameGreaterThanOrEqualTo(String value) {
            addCriterion("wx_user_picname >=", value, "wxUserPicname");
            return (Criteria) this;
        }

        public Criteria andWxUserPicnameLessThan(String value) {
            addCriterion("wx_user_picname <", value, "wxUserPicname");
            return (Criteria) this;
        }

        public Criteria andWxUserPicnameLessThanOrEqualTo(String value) {
            addCriterion("wx_user_picname <=", value, "wxUserPicname");
            return (Criteria) this;
        }

        public Criteria andWxUserPicnameLike(String value) {
            addCriterion("wx_user_picname like", value, "wxUserPicname");
            return (Criteria) this;
        }

        public Criteria andWxUserPicnameNotLike(String value) {
            addCriterion("wx_user_picname not like", value, "wxUserPicname");
            return (Criteria) this;
        }

        public Criteria andWxUserPicnameIn(List<String> values) {
            addCriterion("wx_user_picname in", values, "wxUserPicname");
            return (Criteria) this;
        }

        public Criteria andWxUserPicnameNotIn(List<String> values) {
            addCriterion("wx_user_picname not in", values, "wxUserPicname");
            return (Criteria) this;
        }

        public Criteria andWxUserPicnameBetween(String value1, String value2) {
            addCriterion("wx_user_picname between", value1, value2, "wxUserPicname");
            return (Criteria) this;
        }

        public Criteria andWxUserPicnameNotBetween(String value1, String value2) {
            addCriterion("wx_user_picname not between", value1, value2, "wxUserPicname");
            return (Criteria) this;
        }

        public Criteria andWxQuestionTypeIsNull() {
            addCriterion("wx_question_type is null");
            return (Criteria) this;
        }

        public Criteria andWxQuestionTypeIsNotNull() {
            addCriterion("wx_question_type is not null");
            return (Criteria) this;
        }

        public Criteria andWxQuestionTypeEqualTo(String value) {
            addCriterion("wx_question_type =", value, "wxQuestionType");
            return (Criteria) this;
        }

        public Criteria andWxQuestionTypeNotEqualTo(String value) {
            addCriterion("wx_question_type <>", value, "wxQuestionType");
            return (Criteria) this;
        }

        public Criteria andWxQuestionTypeGreaterThan(String value) {
            addCriterion("wx_question_type >", value, "wxQuestionType");
            return (Criteria) this;
        }

        public Criteria andWxQuestionTypeGreaterThanOrEqualTo(String value) {
            addCriterion("wx_question_type >=", value, "wxQuestionType");
            return (Criteria) this;
        }

        public Criteria andWxQuestionTypeLessThan(String value) {
            addCriterion("wx_question_type <", value, "wxQuestionType");
            return (Criteria) this;
        }

        public Criteria andWxQuestionTypeLessThanOrEqualTo(String value) {
            addCriterion("wx_question_type <=", value, "wxQuestionType");
            return (Criteria) this;
        }

        public Criteria andWxQuestionTypeLike(String value) {
            addCriterion("wx_question_type like", value, "wxQuestionType");
            return (Criteria) this;
        }

        public Criteria andWxQuestionTypeNotLike(String value) {
            addCriterion("wx_question_type not like", value, "wxQuestionType");
            return (Criteria) this;
        }

        public Criteria andWxQuestionTypeIn(List<String> values) {
            addCriterion("wx_question_type in", values, "wxQuestionType");
            return (Criteria) this;
        }

        public Criteria andWxQuestionTypeNotIn(List<String> values) {
            addCriterion("wx_question_type not in", values, "wxQuestionType");
            return (Criteria) this;
        }

        public Criteria andWxQuestionTypeBetween(String value1, String value2) {
            addCriterion("wx_question_type between", value1, value2, "wxQuestionType");
            return (Criteria) this;
        }

        public Criteria andWxQuestionTypeNotBetween(String value1, String value2) {
            addCriterion("wx_question_type not between", value1, value2, "wxQuestionType");
            return (Criteria) this;
        }

        public Criteria andReplyHumanIsNull() {
            addCriterion("reply_human is null");
            return (Criteria) this;
        }

        public Criteria andReplyHumanIsNotNull() {
            addCriterion("reply_human is not null");
            return (Criteria) this;
        }

        public Criteria andReplyHumanEqualTo(String value) {
            addCriterion("reply_human =", value, "replyHuman");
            return (Criteria) this;
        }

        public Criteria andReplyHumanNotEqualTo(String value) {
            addCriterion("reply_human <>", value, "replyHuman");
            return (Criteria) this;
        }

        public Criteria andReplyHumanGreaterThan(String value) {
            addCriterion("reply_human >", value, "replyHuman");
            return (Criteria) this;
        }

        public Criteria andReplyHumanGreaterThanOrEqualTo(String value) {
            addCriterion("reply_human >=", value, "replyHuman");
            return (Criteria) this;
        }

        public Criteria andReplyHumanLessThan(String value) {
            addCriterion("reply_human <", value, "replyHuman");
            return (Criteria) this;
        }

        public Criteria andReplyHumanLessThanOrEqualTo(String value) {
            addCriterion("reply_human <=", value, "replyHuman");
            return (Criteria) this;
        }

        public Criteria andReplyHumanLike(String value) {
            addCriterion("reply_human like", value, "replyHuman");
            return (Criteria) this;
        }

        public Criteria andReplyHumanNotLike(String value) {
            addCriterion("reply_human not like", value, "replyHuman");
            return (Criteria) this;
        }

        public Criteria andReplyHumanIn(List<String> values) {
            addCriterion("reply_human in", values, "replyHuman");
            return (Criteria) this;
        }

        public Criteria andReplyHumanNotIn(List<String> values) {
            addCriterion("reply_human not in", values, "replyHuman");
            return (Criteria) this;
        }

        public Criteria andReplyHumanBetween(String value1, String value2) {
            addCriterion("reply_human between", value1, value2, "replyHuman");
            return (Criteria) this;
        }

        public Criteria andReplyHumanNotBetween(String value1, String value2) {
            addCriterion("reply_human not between", value1, value2, "replyHuman");
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

        public Criteria andCompletetimeIsNull() {
            addCriterion("completeTime is null");
            return (Criteria) this;
        }

        public Criteria andCompletetimeIsNotNull() {
            addCriterion("completeTime is not null");
            return (Criteria) this;
        }

        public Criteria andCompletetimeEqualTo(String value) {
            addCriterion("completeTime =", value, "completetime");
            return (Criteria) this;
        }

        public Criteria andCompletetimeNotEqualTo(String value) {
            addCriterion("completeTime <>", value, "completetime");
            return (Criteria) this;
        }

        public Criteria andCompletetimeGreaterThan(String value) {
            addCriterion("completeTime >", value, "completetime");
            return (Criteria) this;
        }

        public Criteria andCompletetimeGreaterThanOrEqualTo(String value) {
            addCriterion("completeTime >=", value, "completetime");
            return (Criteria) this;
        }

        public Criteria andCompletetimeLessThan(String value) {
            addCriterion("completeTime <", value, "completetime");
            return (Criteria) this;
        }

        public Criteria andCompletetimeLessThanOrEqualTo(String value) {
            addCriterion("completeTime <=", value, "completetime");
            return (Criteria) this;
        }

        public Criteria andCompletetimeLike(String value) {
            addCriterion("completeTime like", value, "completetime");
            return (Criteria) this;
        }

        public Criteria andCompletetimeNotLike(String value) {
            addCriterion("completeTime not like", value, "completetime");
            return (Criteria) this;
        }

        public Criteria andCompletetimeIn(List<String> values) {
            addCriterion("completeTime in", values, "completetime");
            return (Criteria) this;
        }

        public Criteria andCompletetimeNotIn(List<String> values) {
            addCriterion("completeTime not in", values, "completetime");
            return (Criteria) this;
        }

        public Criteria andCompletetimeBetween(String value1, String value2) {
            addCriterion("completeTime between", value1, value2, "completetime");
            return (Criteria) this;
        }

        public Criteria andCompletetimeNotBetween(String value1, String value2) {
            addCriterion("completeTime not between", value1, value2, "completetime");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
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