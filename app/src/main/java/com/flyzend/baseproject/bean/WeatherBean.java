package com.flyzend.baseproject.bean;

import java.util.List;

/**
 * Created by 王灿 on 2017/5/3.
 */

public class WeatherBean {

    /**
     * msg : success
     * result : [{"airCondition":"优","city":"南京","coldIndex":"低发期","date":"2017-05-03","distrct":"南京","dressingIndex":"单衣类","exerciseIndex":"不适宜","future":[{"date":"2017-05-03","dayTime":"阴","night":"中雨","temperature":"22°C / 17°C","week":"今天","wind":"东风 4～5级"},{"date":"2017-05-04","dayTime":"阵雨","night":"多云","temperature":"21°C / 15°C","week":"星期四","wind":"西北风 4～5级"},{"date":"2017-05-05","dayTime":"多云","night":"多云","temperature":"25°C / 16°C","week":"星期五","wind":"北风 3～4级"},{"date":"2017-05-06","dayTime":"多云","night":"多云","temperature":"23°C / 14°C","week":"星期六","wind":"北风 3～4级"},{"date":"2017-05-07","dayTime":"多云","night":"阴","temperature":"24°C / 16°C","week":"星期日","wind":"南风 3～4级"},{"date":"2017-05-08","dayTime":"小雨","night":"小雨","temperature":"20°C / 16°C","week":"星期一","wind":"南风 3～4级"},{"date":"2017-05-09","dayTime":"少云","night":"晴","temperature":"27°C / 17°C","week":"星期二","wind":"西北偏西风 3级"},{"date":"2017-05-10","dayTime":"晴","night":"少云","temperature":"32°C / 19°C","week":"星期三","wind":"西南偏南风 3级"},{"date":"2017-05-11","dayTime":"局部多云","night":"局部多云","temperature":"29°C / 18°C","week":"星期四","wind":"西风 2级"},{"date":"2017-05-12","dayTime":"局部多云","night":"零散阵雨","temperature":"29°C / 19°C","week":"星期五","wind":"西南偏西风 2级"}],"humidity":"湿度：66%","pollutionIndex":"39","province":"江苏","sunrise":"05:17","sunset":"18:47","temperature":"23℃","time":"13:21","updateTime":"20170503134256","washIndex":"不适宜","weather":"阴","week":"周三","wind":"东南风5级"}]
     * retCode : 200
     */

    private String msg;
    private String retCode;
    private List<ResultBean> result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * airCondition : 优
         * city : 南京
         * coldIndex : 低发期
         * date : 2017-05-03
         * distrct : 南京
         * dressingIndex : 单衣类
         * exerciseIndex : 不适宜
         * future : [{"date":"2017-05-03","dayTime":"阴","night":"中雨","temperature":"22°C / 17°C","week":"今天","wind":"东风 4～5级"},{"date":"2017-05-04","dayTime":"阵雨","night":"多云","temperature":"21°C / 15°C","week":"星期四","wind":"西北风 4～5级"},{"date":"2017-05-05","dayTime":"多云","night":"多云","temperature":"25°C / 16°C","week":"星期五","wind":"北风 3～4级"},{"date":"2017-05-06","dayTime":"多云","night":"多云","temperature":"23°C / 14°C","week":"星期六","wind":"北风 3～4级"},{"date":"2017-05-07","dayTime":"多云","night":"阴","temperature":"24°C / 16°C","week":"星期日","wind":"南风 3～4级"},{"date":"2017-05-08","dayTime":"小雨","night":"小雨","temperature":"20°C / 16°C","week":"星期一","wind":"南风 3～4级"},{"date":"2017-05-09","dayTime":"少云","night":"晴","temperature":"27°C / 17°C","week":"星期二","wind":"西北偏西风 3级"},{"date":"2017-05-10","dayTime":"晴","night":"少云","temperature":"32°C / 19°C","week":"星期三","wind":"西南偏南风 3级"},{"date":"2017-05-11","dayTime":"局部多云","night":"局部多云","temperature":"29°C / 18°C","week":"星期四","wind":"西风 2级"},{"date":"2017-05-12","dayTime":"局部多云","night":"零散阵雨","temperature":"29°C / 19°C","week":"星期五","wind":"西南偏西风 2级"}]
         * humidity : 湿度：66%
         * pollutionIndex : 39
         * province : 江苏
         * sunrise : 05:17
         * sunset : 18:47
         * temperature : 23℃
         * time : 13:21
         * updateTime : 20170503134256
         * washIndex : 不适宜
         * weather : 阴
         * week : 周三
         * wind : 东南风5级
         */

        private String airCondition;
        private String city;
        private String coldIndex;
        private String date;
        private String distrct;
        private String dressingIndex;
        private String exerciseIndex;
        private String humidity;
        private String pollutionIndex;
        private String province;
        private String sunrise;
        private String sunset;
        private String temperature;
        private String time;
        private String updateTime;
        private String washIndex;
        private String weather;
        private String week;
        private String wind;
        private List<FutureBean> future;

        public String getAirCondition() {
            return airCondition;
        }

        public void setAirCondition(String airCondition) {
            this.airCondition = airCondition;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getColdIndex() {
            return coldIndex;
        }

        public void setColdIndex(String coldIndex) {
            this.coldIndex = coldIndex;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getDistrct() {
            return distrct;
        }

        public void setDistrct(String distrct) {
            this.distrct = distrct;
        }

        public String getDressingIndex() {
            return dressingIndex;
        }

        public void setDressingIndex(String dressingIndex) {
            this.dressingIndex = dressingIndex;
        }

        public String getExerciseIndex() {
            return exerciseIndex;
        }

        public void setExerciseIndex(String exerciseIndex) {
            this.exerciseIndex = exerciseIndex;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public String getPollutionIndex() {
            return pollutionIndex;
        }

        public void setPollutionIndex(String pollutionIndex) {
            this.pollutionIndex = pollutionIndex;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getSunrise() {
            return sunrise;
        }

        public void setSunrise(String sunrise) {
            this.sunrise = sunrise;
        }

        public String getSunset() {
            return sunset;
        }

        public void setSunset(String sunset) {
            this.sunset = sunset;
        }

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getWashIndex() {
            return washIndex;
        }

        public void setWashIndex(String washIndex) {
            this.washIndex = washIndex;
        }

        public String getWeather() {
            return weather;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
        }

        public String getWind() {
            return wind;
        }

        public void setWind(String wind) {
            this.wind = wind;
        }

        public List<FutureBean> getFuture() {
            return future;
        }

        public void setFuture(List<FutureBean> future) {
            this.future = future;
        }

        public static class FutureBean {
            /**
             * date : 2017-05-03
             * dayTime : 阴
             * night : 中雨
             * temperature : 22°C / 17°C
             * week : 今天
             * wind : 东风 4～5级
             */

            private String date;
            private String dayTime;
            private String night;
            private String temperature;
            private String week;
            private String wind;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getDayTime() {
                return dayTime;
            }

            public void setDayTime(String dayTime) {
                this.dayTime = dayTime;
            }

            public String getNight() {
                return night;
            }

            public void setNight(String night) {
                this.night = night;
            }

            public String getTemperature() {
                return temperature;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }

            public String getWeek() {
                return week;
            }

            public void setWeek(String week) {
                this.week = week;
            }

            public String getWind() {
                return wind;
            }

            public void setWind(String wind) {
                this.wind = wind;
            }
        }
    }
}
