SELECT CART_ID
FROM
(
    SELECT CART_ID
         , SUM(CASE WHEN NAME in ('Milk')THEN 1 ELSE 0 END) AS milk_cnt
         , SUM(CASE WHEN NAME in ('Yogurt')THEN 1 ELSE 0 END) AS yogurt_cnt
        FROM CART_PRODUCTS
        WHERE 1=1
        GROUP BY CART_ID
) T
WHERE 1=1
  AND milk_cnt > 0
  AND yogurt_cnt > 0
ORDER BY CART_ID;