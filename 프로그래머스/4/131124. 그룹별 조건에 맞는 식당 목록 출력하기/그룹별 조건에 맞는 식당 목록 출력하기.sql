-- 코드를 입력하세요
SELECT P.MEMBER_NAME, R2.REVIEW_TEXT, TO_CHAR(R2.REVIEW_DATE, 'YYYY-MM-DD')
FROM MEMBER_PROFILE P
JOIN (
    SELECT R.MEMBER_ID, COUNT(R.MEMBER_ID)
    FROM REST_REVIEW R
    GROUP BY R.MEMBER_ID
    ORDER BY COUNT(R.MEMBER_ID) DESC
    FETCH FIRST 1 ROW ONLY
) T ON P.MEMBER_ID = T.MEMBER_ID
JOIN REST_REVIEW R2 ON P.MEMBER_ID = R2.MEMBER_ID
ORDER BY R2.REVIEW_DATE, R2.REVIEW_TEXT;