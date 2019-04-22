## project Arducon project

## project manager: keelim
## start date: March 1
## complete date: June 30  

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

- - -

## 2019 04 14
1. 앱바 --> 액션바, 툴바 2가지로 구성을 할 수 있다. 
    - Actionbar old version,  Toolbar latest version
    - Toolbar > Actionbar
    - 툴바를 잡아주고
    - menu.xml를 작성
    - 툴바를 이용하여 setSupportActionbar() 를 통하여 잡아주고 --> code 적 이용방법 
    - 액션바의 설정을 이용을 한다. 
    
2. 드로어 레이아웃 설정
    - 드로어 레이아웃의 설정의 버튼 
    - R.id.home 으로 표현을 한다.
    <code>drawerLayout.openDrawer(GravityCompat.START);</code>
    <code>drawerLayout.closeDrawers()</code>
    드로어 레이아웃을 닫아준다. 
     
3. Toast 메시지 보다는 SnackBar 깔끔할 수 도 있을 것이라는 생각을 해본다.
    <code>SnackBar.make(Toolbae, "", Snackbar.SHORT").show</code>
- - -
## 2019 04 15
1. temp drawer layout 
2. ready to the other setting
    - 아이콘 생성 준비
    - 블루투스 코드 수정
    - 레이아웃 기능 최소화
    - 드로어 레이아웃 정리
    - component 확인
    - flow chart
    x
3. Test adMob setting 
    - 출시 시 adMob id 변경을 할 것
    - xml도 변경을 해야지
    - code, @string/addMobid 
    
## 2019 04 16
1. drawer layout  --> 슬라이드 메뉴?
    - drawer 레이아웃으로 적용 --> 드로어 레이아웃 정상 작동 확인 
    - 드로어 레이아웃 수정을 할 것
    - 드로어 레이아웃 수정 완료 --> 작동 확인
    - R.id.home 사용을 하는 방법
     <code> <include layout="@layout/apply_darwer" /> </code>
2. 전체적인 레이아웃 수정 준비 --> 하나의 액티비티 수정을 할 필요가 있나?
    
3. 드로어 레이아웃 홈버튼 활성화 완료
    - android.R.id.home 으로 접근을 해야 할 수 있다는것을 기억을 할 것
    
    
## 2019 04 17
1. 아이콘 생성 준비
2. 블루 투스 코드 수정
3. drawerView 이벤트 리스터 작동을 할 것
4. component 확인

## 2019 04 18
1. 아이콘 생성
    - 딱히 생성을 할 게 있나?
    - navigation view 는 설정을 하는 것이 좋을 것 같다.

## 2019 04 20
1. 개발 완료
2. 지속적인 업데이트 진행
3. 버전 코드 수정
    - app --> gradle version name and version
    - BuildConfig 수정 안해도 된다. 
4. 프로그램 개발 계획
    - 블루 투스 코드 수정
    - 충돌 볼고서 (Crashtyics의 상태 확인)
    - 다른 컴포넌트 사용성 개선
    - 색깔 전부 수정 --> 파란색으로
    - 설정 기능 먼저 준비
    - 릴리즈 노트 작성 
    - developer console 수정
    - 액티비티 기능들 준비 할 것
5. gradle 3.4.0 error
    - 이유를 모르겠다. 호환이 안되는 건가?
6. 매주 목요일 업데이트
7. drawer view snackbar.
    - fragment로 작동을 시키기?
    - implements OnNavigationItemSelectedListener
8. Toggle?
    - Boolean switch?
    - Button?
    
## 2019 04 21
1. 블루투스 코드 1차 수정
    - 임의로 finish 하는 부분 제거
    - Buffer 부분은 수정 필요
2. 설정 부분은 새로 만들 거나 fragment 부분으로 사용하는 것이 좋을 듯
3. 안드로이드 인트로 화면 개발
    - 1초 정도만 보여주고 종료 시키는 것
    
## 2019 04 22
1. 블루투스 코드 2차 수정
    - 블루투스 코드 간소화
    - 오류가 있는 부분은 수정을 할 것
2. 내부 UI 수정
    - 새로운 액티비티 추가를 할 것
3. 내부 동작 수정
    - 액티비티 하이라키 준비
4. 기능 
    - 블루투스 테스트 기능 준비?
        - 아두이노와 블루투스가 통신이 되는지를 체크 하는 기능
        - like ping()
    - 블루 투스 코드 수정
    - 충돌 보고서 (Crashtyics의 상태 확인)
    - 다른 컴포넌트 사용성 개선
    - 색깔 전부 수정 --> 파란색으로
    - 설정 기능 먼저 준비
    - 릴리즈 노트 작성 
    - developer console 수정
    - 액티비티 기능들 준비 할 것
