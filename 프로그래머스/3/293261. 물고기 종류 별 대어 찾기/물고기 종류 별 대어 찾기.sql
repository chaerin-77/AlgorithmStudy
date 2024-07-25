-- 코드를 작성해주세요
select  A.ID, 
        B.FISH_NAME,
        C.LENGTH 
 from FISH_INFO a ,
      FISH_NAME_INFO b,
      (select a.FISH_TYPE, MAX(LENGTH) as LENGTH 
       from FISH_INFO a 
       group by a.FISH_TYPE) c 
where  a.FISH_TYPE = b.FISH_TYPE 
  and  a.FISH_TYPE = c.FISH_TYPE
  and  a.LENGTH = c.LENGTH