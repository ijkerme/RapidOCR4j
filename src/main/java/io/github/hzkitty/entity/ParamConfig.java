package io.github.hzkitty.entity;

import lombok.Data;

@Data
public class ParamConfig {
    public Float boxThresh; // 边框阈值
    public Float unclipRatio; // 非极大值抑制后的扩展比例
    public Boolean returnWordBox; // 是否返回单词级别的框
    public Boolean returnWordLevel; // 若为true，则返回单词级别的坐标框，否则返回字母级别
    public Boolean useDet; // 是否使用检测模块
    public Boolean useCls; // 是否使用分类模块
    public Boolean useRec; // 是否使用识别模块
}
