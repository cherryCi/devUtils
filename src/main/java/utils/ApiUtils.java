package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author cherry.cui
 * @version 1.0
 * @date 2021/2/23 21:10
 * @desc jdk8  常用类 使用示例
 */
public class ApiUtils {


    static class Bike{

        private String bikeVersion;

        private String desc;

        private City city;

        public String getBikeVersion() {
            return bikeVersion;
        }

        public void setBikeVersion(String bikeVersion) {
            this.bikeVersion = bikeVersion;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public City getCity() {
            return city;
        }

        public void setCity(City city) {
            this.city = city;
        }
    }

    static class City{

        private String cityName;

        private Area  area;

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public Area getArea() {
            return area;
        }

        public void setArea(Area area) {
            this.area = area;
        }
    }

    static class Area{
        private boolean center;

        private String areaName;

        public boolean isCenter() {
            return center;
        }

        public void setCenter(boolean center) {
            this.center = center;
        }

        public String getAreaName() {
            return areaName;
        }

        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }
    }

    /**
     * optional 类 demo
     *  ofNullable 方法判断当前对象是否为空，接着按照字段映射一层一层往下判断，省去了很多if-else
     */
    public void OptionalDemo(){
        //获取area名称
        Bike bike = new Bike();
        //为空返回null, 否则返回areaName
        Optional.ofNullable(bike).map(Bike::getCity).map(City::getArea).map(Area::getAreaName).orElse(null);
        //非中心字段返回默认值
        Optional.ofNullable(bike).map(Bike::getCity).map(City::getArea).map(Area::isCenter).orElse(false);
    }

    static class Task{
        private String taskName;

        private Integer fetchType;

        private Integer taskType;

        private int taskCount;

        public String getTaskName() {
            return taskName;
        }

        public void setTaskName(String taskName) {
            this.taskName = taskName;
        }

        public Integer getFetchType() {
            return fetchType;
        }

        public void setFetchType(Integer fetchType) {
            this.fetchType = fetchType;
        }

        public Integer getTaskType() {
            return taskType;
        }

        public void setTaskType(Integer taskType) {
            this.taskType = taskType;
        }

        public int getTaskCount() {
            return taskCount;
        }

        public void setTaskCount(int taskCount) {
            this.taskCount = taskCount;
        }
    }

    /**
     * stream 类 demo
     */
    public void streamDemo(){
        List<Task> taskList = new ArrayList<>();
        // 根据某个字段分组
        Map<Integer, List<Task>> taskMap =taskList.stream().collect(Collectors.groupingBy( Task::getTaskType ));
        //生成map
        Map<Integer, Task> appleMap = taskList.stream().collect(Collectors.toMap(Task::getFetchType, a->a,(key1,key2)->key1));
        //过滤
        List<Task> filterList = taskList.stream().filter(task -> task.getFetchType().equals(1)).collect(Collectors.toList());
    }

}
