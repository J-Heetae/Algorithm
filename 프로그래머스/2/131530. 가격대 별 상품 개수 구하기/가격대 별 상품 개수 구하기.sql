-- 코드를 입력하세요
SELECT TRUNC(PRICE / 10000) * 10000 AS PRICE_GROUP,
       COUNT(*) AS PRODUCTS
FROM PRODUCT
GROUP BY TRUNC(PRICE / 10000) * 10000
ORDER BY PRICE_GROUP ASC;