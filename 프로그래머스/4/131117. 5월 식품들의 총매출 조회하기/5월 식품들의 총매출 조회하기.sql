SELECT P.PRODUCT_ID, P.PRODUCT_NAME, (SUM(F.AMOUNT) * P.PRICE) AS TOTAL_SALES
FROM FOOD_ORDER F
JOIN FOOD_PRODUCT P ON F.PRODUCT_ID = P.PRODUCT_ID
WHERE F.PRODUCE_DATE BETWEEN TO_DATE('2022-05-01', 'YYYY-MM-DD') AND TO_DATE('2022-05-31', 'YYYY-MM-DD')
GROUP BY P.PRODUCT_ID, P.PRODUCT_NAME, P.PRICE
ORDER BY TOTAL_SALES DESC, P.PRODUCT_ID ASC;