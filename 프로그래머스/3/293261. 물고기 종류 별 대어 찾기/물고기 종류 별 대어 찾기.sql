SELECT
    I.ID,
    N.FISH_NAME,
    I.LENGTH
FROM FISH_INFO I
JOIN (
    SELECT FISH_TYPE, MAX(LENGTH) AS MAX_LENGTH
    FROM FISH_INFO
    WHERE LENGTH IS NOT NULL
    GROUP BY FISH_TYPE
) M ON I.FISH_TYPE = M.FISH_TYPE AND I.LENGTH = M.MAX_LENGTH
JOIN FISH_NAME_INFO N ON I.FISH_TYPE = N.FISH_TYPE
ORDER BY I.ID ASC;