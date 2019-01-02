/*
 * Copyright (c) 2019 josephguan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import sbt._
import Keys._
import scala.collection.mutable.ArrayBuffer

object Builds extends Build {

  val modules: ArrayBuffer[Project] = ArrayBuffer[Project]()

  //----------------------------
  // problems
  //----------------------------
  lazy val `leetcode` = project.in(file("problems/leetcode")).settings(name := "leetcode").
    settings(Common.settings: _*).
    settings(libraryDependencies ++= Dependencies.test)
  modules += `leetcode`

  lazy val `ninety-nine` = project.in(file("problems/ninety-nine")).settings(name := "ninety-nine").
    settings(Common.settings: _*).
    settings(libraryDependencies ++= Dependencies.test)
  modules += `ninety-nine`

  lazy val `conway` = project.in(file("problems/conway")).settings(name := "conway").
    settings(Common.settings: _*).
    settings(libraryDependencies ++= Dependencies.test)
  modules += `conway`

  lazy val `stackoverflow` = project.in(file("problems/stackoverflow")).settings(name := "stackoverflow").
    settings(Common.settings: _*).
    settings(libraryDependencies ++= Dependencies.test)
  modules += `stackoverflow`

  //----------------------------
  // books
  //----------------------------
  lazy val `fpinscala` = project.in(file("books/fpinscala")).settings(name := "fpinscala").
    settings(Common.settings: _*).
    settings(libraryDependencies ++= Dependencies.test)
  modules += `fpinscala`


  //----------------------------
  // trainings
  //----------------------------
  lazy val `scratch` = project.in(file("trainings/getting-started")).settings(name := "getting-started").
    settings(Common.settings: _*).
    settings(libraryDependencies ++= Dependencies.test)
  modules += `scratch`

  lazy val `type-system` = project.in(file("trainings/type-system")).settings(name := "type-system").
    settings(Common.settings: _*).
    settings(libraryDependencies ++= Dependencies.test)
  modules += `type-system`
  
  
  //----------------------------------------
  // all in one
  //----------------------------------------
  lazy val `scala-lab` = (project in file(".")).settings(name := "scala-lab").
    settings(Common.settings: _*).
    aggregate(modules.map(Project.projectToRef): _*)

}
