-- 코드를 입력하세요
SELECT
    b.title,
    b.board_id,
    r.reply_id,
    r.writer_id,
    r.contents,
    TO_CHAR(r.created_date, 'YYYY-MM-DD') "CREATED_DATE"
FROM used_goods_board b, used_goods_reply r
WHERE b.board_id = r.board_id
    AND TO_CHAR(b.created_date, 'YYYY-MM') = '2022-10' 
ORDER BY r.created_date ASC, b.title ASC;