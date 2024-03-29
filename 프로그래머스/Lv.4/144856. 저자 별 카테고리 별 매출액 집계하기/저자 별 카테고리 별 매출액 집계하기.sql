-- 코드를 입력하세요
SELECT B.AUTHOR_ID,
       A.AUTHOR_NAME,
       B.CATEGORY,
       SUM(S.SALES * B.PRICE) AS TOTAL_SALES
FROM BOOK B
    JOIN AUTHOR A ON B.AUTHOR_ID = A.AUTHOR_ID
    JOIN BOOK_SALES S ON B.BOOK_ID = S.BOOK_ID
WHERE S.SALES_DATE >= TO_DATE('2022-01-01', 'YYYY-MM-DD')
    AND S.SALES_DATE < TO_DATE('2022-02-01', 'YYYY-MM-DD')
GROUP BY B.AUTHOR_ID,B.CATEGORY, A.AUTHOR_NAME
ORDER BY B.AUTHOR_ID ASC, B.CATEGORY DESC;
