# ID별 GENERATION 구하기
# 이후 모든 PARENT_ID를 모아서
# PARENT_ID에 포함되지 않는 ID들만 모음
# 해당 ID들을 GENERATION으로 묶어서 카운팅

WITH RECURSIVE generation_tbl AS (
    SELECT
        ID,
        PARENT_ID,
        1 AS GENERATION
    FROM ECOLI_DATA
    WHERE PARENT_ID IS NULL
    
    UNION ALL
    
    SELECT
        child.ID,
        child.PARENT_ID,
        parent.GENERATION + 1 AS GENERATION
    FROM ECOLI_DATA child
    INNER JOIN generation_tbl parent 
        ON parent.ID = child.PARENT_ID
)

SELECT
    COUNT(*) AS COUNT,
    G.GENERATION
FROM ECOLI_DATA E
JOIN generation_tbl G ON E.ID = G.ID
WHERE E.ID NOT IN (
    SELECT DISTINCT PARENT_ID
    FROM ECOLI_DATA
    WHERE PARENT_ID IS NOT NULL
)
GROUP BY G.GENERATION
ORDER BY G.GENERATION ASC;