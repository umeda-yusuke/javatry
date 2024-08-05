/*
 * Copyright 2019-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.javatry.basic.st6.os;

/**
 * @author jflute
 * @author umeda-yusuke
 */
public abstract class St6OperationSystem {

    // TODO umeyan なんか Definition のアゴが外れてる？(^^。まあ、定数無くなっちゃったのでそもそも削除でもOKですね by jflute (2024/08/06)
    // TODO jflute 1on1にて消しすぎ注意の話 (消しすぎたのかどうかわからないけど) (2024/08/06)
    // ===================================================================================
    //                                                                          Definition

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final String osType;
    private final String loginId;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public St6OperationSystem(String osType, String loginId) {
        this.osType = osType;
        this.loginId = loginId;
    }

    // ===================================================================================
    //                                                                      User Directory
    //                                                                      ==============
    public String buildUserResourcePath(String relativePath) {
        String fileSeparator = getFileSeparator();
        String userDirectory = getUserDirectory();
        String resourcePath = userDirectory + fileSeparator + relativePath;
        return resourcePath.replace("/", fileSeparator);
    }

    protected abstract String getFileSeparator();

    protected abstract String getUserDirectory();

    public String getOsType(){
        return osType;
    }

    public String getLoginId(){
        return loginId;
    }
}
