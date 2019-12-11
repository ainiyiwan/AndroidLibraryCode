package com.zy.androidlibrarycode.lombok;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * ================================================
 * 作    者：zhangyang
 * 创建日期：2019/11/7
 * 描    述：
 * ================================================
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class LombokEntity {
    @NonNull
    private String name;
    private int age;
}
