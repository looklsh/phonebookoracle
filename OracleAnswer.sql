-- Q1.
SELECT last_name, salary, department_name,commission_pct
FROM employees emp, departments dept
WHERE emp.department_id = dept.department_id(+)
AND commission_pct IS NOT NULL;


-- Q2.
SELECT emp.last_name, emp.salary, emp.job_id
FROM employees emp JOIN employees man
ON emp.manager_id = man.employee_id
WHERE man.last_name = 'King';


-- Q3.
SELECT emp.last_name, emp.salary
FROM employees emp JOIN employees man ON emp.manager_id = man.employee_id
WHERE emp.salary > man.salary;


-- Q4.
SELECT MIN(salary), MAX(salary), SUM(salary), CEIL(AVG(salary))
FROM employees;


-- Q5.
SELECT last_name, salary
FROM employees outer
WHERE salary < (SELECT AVG(salary) FROM employees WHERE department_id = outer.department_id);


