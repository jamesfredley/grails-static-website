/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.grails.gradle

import groovy.transform.CompileStatic
import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import org.grails.documentation.DownloadPage

@CompileStatic
class DownloadTask extends DefaultTask {

    static final String PAGE_NAME_DOCS = "download.html"

    @Input
    final Property<File> releases = project.objects.property(File)

    @Input
    final Property<String> url = project.objects.property(String)

    @OutputDirectory
    final Property<File> output = project.objects.property(File)

    @TaskAction
    void renderDocsPage() {
        File build = output.get()
        File temp = new File(build.absolutePath + "/" + DocumentationTask.TEMP)
        temp.mkdir()

        File output = new File(temp.getAbsolutePath() + "/" + PAGE_NAME_DOCS)
        output.createNewFile()
        output.text = "title: Downloads | Apache Grails&reg;\nbody: docs\n---\n" +
                DownloadPage.mainContent(releases.get())
    }
}
