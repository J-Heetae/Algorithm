with car as (
    select com.car_id
        , com.car_type
        , trunc((30 * com.daily_fee) * ((100 - plan.discount_rate) / 100)) FEE 
    from car_rental_company_car com
    left outer join car_rental_company_discount_plan plan
    on com.car_type = plan.car_type
    where com.car_type in ('세단', 'SUV')
        and plan.duration_type = '30일 이상'
        and com.car_id not in (
            select car_id
            from car_rental_company_rental_history
            where to_char(end_date, 'yyyymmdd') >= 20221101
        )
)

select * from car
where fee >= 500000 and fee < 2000000
order by fee desc, car_type asc, car_id desc