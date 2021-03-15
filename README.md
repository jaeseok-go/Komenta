# Komenta

[TOC]


## 팀원 소개

- 테크리더 : 배상웅

- 기획 : 정혜림

- QA : 김채린, 박수아

- Git Master : 고재석



## 주제

- 프로젝트명 : Komenta

  > 독일어 'Kommentar'에서 파생. (Commentary)

- VOD 영상 플랫폼에서 영상 재생 시간별로 댓글과 연동하여 사용자에게 더 큰 재미를 주는 서비스를 제공한다.



## 기능

1. 회원 관리

2. 로그인 관리

3. 어드민 기능

4. VOD 관리

5. 화면 구성



## 개발문서

https://docs.google.com/spreadsheets/d/1f44yO4aeAmSMDSMzdjwmA0yPJ5i3DKCw0QpL_Lu572M/edit#gid=0



## Gantt Chart

```mermaid
gantt
    dateFormat  MM-DD
    title       Komenta 진행상황
    %% excludes    weekends
    %% (`excludes` accepts specific dates in YYYY-MM-DD format, days of the week ("sunday") or "weekends", but not the word "weekdays".)

    section 프로젝트 준비
    주제 선정				  :done, 01-11, 01-14
    기능 목록 상세 도출			:done, 01-14, 3d
    화면 기획				  :done, 01-14, 3d
    개발 환경 구성             :done, 01-12, 2d
    ERD                     : 01-18, 2d

    section Auth server
    MariaDB 구축 및 백업구현     	 :01-19, 2d
    User CRUD 구현 		   :01-22, 3d
    User SNS Login API          :01-25, 2d
    User Session(JWT 인증)       :01-26, 1-27

    section Admin
    Video Upload Test       :01-26, 2d
    Video Crud          :01-27, 3d

    section vod
    VOD 시청 목록   :02-01, 2d
    VOD 상세 정보   :02-02, 3d
    VOD 댓글 정보   :02-02, 3d
    
    section myFeed
    Follow 기능            :02-04,2d
    VOD 플레이리스트 생성   :02-04, 2d

    section Unite
    front-back 합치기  :02-08, 3d
    
    section Result
    CSS 디자인         :02-09, 5d
    ChekcList       : 02-15, 3d
    UCC           :02-16, 2d
    
    %%section 기타
```

## Stack

<img src="https://img.shields.io/badge/Spring-success?style=flat-square&logo=Spring&logoColor=white"/></a>
<img src="https://img.shields.io/badge/IntelliJ-9cf?style=flat-square&logo=intelliJ IDEA&logoColor=white"/></a>
<img src="https://img.shields.io/badge/Gradle-inactive?style=flat-square&logo=Gradle&logoColor=white"/></a>

<img src="https://img.shields.io/badge/Vue.js-green?style=flat-square&logo=Vue.js&logoColor=white"/></a>
<img src="https://img.shields.io/badge/VSCode-informational?style=flat-square&logo=Visual Studio Code&logoColor=white"/></a>

<img src="https://img.shields.io/badge/MariaDB-informational?style=flat-square&logo=MariaDB&logoColor=white"/></a>
<img src="https://img.shields.io/badge/Docker-informational?style=flat-square&logo=Docker&logoColor=white"/></a>


## Code Style



## Git Rule

### branch

`master -> develop -> feature/FE/{기능이름}-{상세기능}(김채린, 박수아, 정혜림) or feature/BE/{기능이름}-{상세기능}(고재석, 배상웅)`

### merge

``

### commit

``








## ERD


![image-erd](README.assets/image-erd.png)