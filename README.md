## project Arducon project

## project manager: keelim
## start date: June 1
## complete date: May 30  

- - -
총 화면은 가지로 구성을 한다. 
main
controller 
Bluetooth setting 
app setiing 

## want adding skills
1. app login
2. adMob --> 광고 앱은 무료로 넣고 광고를 넣는 것이 현명할 것으로 사료
3. apply material design 


## 2019 03 29
1. sample 코드 
2. 코드 분석
3. controller layout 구현 --> 액티비티 생성
4. ~~firebase 연결을 할 것~~
5. ~~firebase basic test~~
6. adMob 은 일단 미뤄두기

## 2019 03 30
1.안드로이드 레이아웃 작성 
2. 아이콘 만들기

## 2019 04 02
1. 안드로이드 아이콘 만들기
2. Fragment를 확인을 할 수 는 있지만 있지만 일단은 보류
3. 액티비티 3개는 확실히 한다. --> 일단 xml 과정에서 진행을 하는 것이다.
    - 액티비티 전환 과정 main, bluetooth setting, 아두이노 컨트롤러
4. 블루투스 통신 모듈 준비 --> 아두이노.ino 파일 예제 작성
5. firebase test
  

## 2019 04 05
1. controller xml 수정
2. appBar 오버플로우 아이템 생성 --> 앱바의 사용은 무조건 오버플로우 액션으로
3. 화면 간의 전환 액티비티를 사용
4. manifest의 액티비티를 등록을 해야 작동을 한다. 
5. 수정 사항 controller landscape 로 변경 할 것

## 2019 04 08
1. UUID ? 
    - 간단한 유니크한 아이디
    - 내부 저장소 에서 관리를 해야 한다.  
    <code> String UUID = UUID.randomUUID().toString() </code>
    
2. Handler 조정 방법
3. StringBuilder
    - String을 생성을 하여 두개를 합치는 것이 생각보다 비효율 적이다. 
    - 문자열을 더할 때 객체를 생성하는 것이 아니라 기존의 데이터에서 더한다.
    - 속도가 빠르다. 상대적 부하가 적다.
    - 긴 문자열은 StringBuilder 가 효율적
    <code>
    Stringbuilder stringbuilder = new StringBuilder();
    stringbuilder.appen("ABC");
    stringbuilder.appen("DEF");
    stringbuilder.toString(); //string 으로 변환을 시킨다. 
    </code>
    
4. 블루투스 작동 구조
5. Setting Activity  toolbar  --> action bar 대체를 할 것

## 2019 04 09
1. 드로우 레이아웃 액션바에서 구현을 할 것
    - 드로우 레이아웃을 액션바에 넣고
    - 액션바에서 아이콘을 클리을 하는 경우 --> 드로우 레이아웃이 나오는 구조가 좋을 듯 하다. 

## 2019 04 13 
1. 드로우 레이아웃 일단 제거를 할 것
2. 

