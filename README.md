# 스프링부트와 하이버네이트를 이용한 , 다시만들어보는 태고의달인 커뮤니티 백엔드 

# 2022 년 5월 29일 개념글 고맙다 

https://gall.dcinside.com/mgallery/board/view/?id=taiko&no=162694&exception_mode=recommend&page=1

응..

# 2022 년 5/13 일 기준 개발 

select 는 mybatis , update , delete , insert 등은 하이버네이트 사용을 위해 하이버네이트 , 마이바티스 configuration 작업 

태고의달인 공식사이트 https://taiko.namco-ch.net/taiko/songlist/namco.php#sgnavi 에서 데이터를 크롤링하여 데이터베이스에 적재작업 , 

# spring data jpa 에서  repository , entity 를 찾지못해 의존성주입을 못하는경우 
<img width="857" alt="스크린샷 2022-05-13 오후 11 11 49" src="https://user-images.githubusercontent.com/69393030/168302292-3fb2964b-34fc-4eae-8596-ecb96bd2b041.png">

@EnableJpaRepositories(basePackages = { "com.taiko.taikoproject.repository" })

@EntityScan(basePackages = { "com.taiko.taikoproject.entity" }) 

을 추가해준다.


# paging 처리 
```java
 Pageable result = PageRequest.of(Integer.parseInt(pageNum), 5, Sort.by("createdTime").descending());
        return new ResponseEntity<>(taikoBoard.findBydeletedTimeNull(result), HttpStatus.OK);

              
 ```
  Pageable 을 이용하여 페이지 계산  , 최근 날짜의 게시물을 가져오도록 정렬.
  
  

