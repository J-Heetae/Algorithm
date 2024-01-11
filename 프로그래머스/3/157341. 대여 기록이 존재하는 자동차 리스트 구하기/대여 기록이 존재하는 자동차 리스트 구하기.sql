SELECT DISTINCT H.CAR_ID
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY H
JOIN CAR_RENTAL_COMPANY_CAR C ON H.CAR_ID = C.CAR_ID
WHERE C.CAR_TYPE = '세단'
    AND H.START_DATE BETWEEN TO_DATE('2022-10-01', 'YYYY-MM-DD') AND TO_DATE('2022-10-31', 'YYYY-MM-DD')
ORDER BY H.CAR_ID DESC;