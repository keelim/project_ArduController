# Project : hard testing applciation

## start: 2020/JUNE

## copyright: jaehyun_kim, keel_im, 2020. 06

## 1. Goal: developing a application for showing the android device information

## 2. Goal: deploying a open source about android application by using jitpack.io

- - -

### 2020 06 18

프로젝트 구성

app - main - (utils, view, model)
view - activity, fragments
model - model, interface, adapter, others...
utils -  like BackPressController and other library

## 2020 06 28

### 테스팅 어플 24~30 연동 완료 이상한 코드 지울 것
### 어플리케이션 UI 개선 할 것 전체
### 오픈소스 jitpack io build 찾아보기

## 2020 06 29

### Builder pattern 으로 클래스 구성 (SYSTEM, BUILD , WIFI 등) --> 추가계획
### ListView 나 RecyclerView 를 통하여 화면 구성 -> CustomView 작성할 것
### Builder Pattern 으로 구성된 -> 적용할 수 있는 Adapter 만들기
### 디자인 검토하기

## 2020 07 05

### application level --> hard application
### library level ---> aio

#### 라이브러리 구성 with kotlin + java

    - class Open (user using this class)
    - class OpenAdapter <- BaseAdapter (user using this class)
    - class OpenRecyclerAdapter <- RecyclerView.Adapter (user usint this class <Recommend>)
    - class OpenItem (Model)
    - view_open.xml(custom view layout)
    - attrs.xml (custom view declare)

`TempActivity` is part of hardware information application in android

## 2020 07
- setting license about this project

#### library manual
1. make a layout file in res\layout (if you want to use this view)

2. writing a view (`OpenView`)
    - like this (`please add a photo`)

3. making a class Open and OpenAdapter(OpenAdapter or Open RecyclerAdapter)
    - recommend RecyclerAdapter

4. you making system datas and other hardware data with class `Open` and `OpenBuilder`
    - OpenBuilder is inner class in the class Open

5. you inject your data in RecyclerAdapter constructor or `setData` method in Activity onCreate method
6.Building and installing your application and Testing your device
    -  test your application in android studio with junit

#### Example

<please add photo about
This project goal is using android knowledge and open source knowledge.
I`m very happy if you give me some advices.
if you have any question about this project, making a issue or sending a mail.

Thank you!!

mail: kimh00335@gmail.com








