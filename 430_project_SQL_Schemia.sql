-- drop table TakesAutoLoan;
-- drop table AutoLoan;

-- drop table TakesPersonalLoan;
-- drop table PersonalLoan;

-- drop table StudentLoan;

-- drop table TakesMortgage;
-- drop table Mortgage;

-- drop table Client;

/* Notes:
	Might want to add How much was paid to StudentLoan table.
*/
create table Client ( 
    SSN numeric(9,0) check (001010001 <= SSN), 
    Name char (50),	 
	Income binary_double check (Income >= 0), 
    primary key (SSN) 
);

create table AutoLoan ( 
    Auto_Loan_ID int check (Auto_Loan_ID > 0), 
    Make char(20), 
    Model char(20), 
	Year int check (1900 <= Year AND Year <= 2030), 
    VIN char(17), 
    Loan_Amount binary_double check (Loan_Amount > 0), 
    Interest_Rate binary_double check (Interest_Rate >= 0), 
    Amount_Paid binary_double default 0 check (Amount_Paid >= 0), --check (Amount_Paid <= Loan_Amount), -- Change this later, to the total value 
    Start_Date char(10),		-- Might change this to date later 
    Number_of_payments int check (Number_of_payments >= 0), 
	End_Date char (10), 		-- check (Start_Date <= End_Date), 
    primary key (Auto_Loan_ID) 
);

create table TakesAutoLoan ( 
    SSN numeric(9,0), 
    Auto_Loan_ID int, 
    primary key (SSN,Auto_Loan_ID), 
    foreign key (SSN) references Client, 
    foreign key (Auto_Loan_ID) references AutoLoan 
);

create table PersonalLoan ( 
    Personal_Loan_ID int check (Personal_Loan_ID > 0), 
    Loan_purpose char(20), 
    Loan_Amount binary_double check (Loan_Amount > 0), 
    Interest_Rate binary_double check (Interest_Rate >= 0), 
    Amount_Paid binary_double default 0 check (Amount_Paid >= 0), 		-- check Amount <= total value, see if I can make this a default to zero 
	Start_Date char(10), 
    Number_of_Payments int default 0,   -- have a trigger that updates this everytime a loan is payed back.  
    End_Date char(10),				-- trigger check Start_Date <= End_Date 
	primary key (Personal_Loan_ID) 
);

create table TakesPersonalLoan ( 
    SSN numeric(9,0), 
    Personal_Loan_ID int, 
    primary key (SSN,Personal_Loan_ID), 
    foreign key (SSN) references Client, 
    foreign key (Personal_Loan_ID) references PersonalLoan 
);

create table StudentLoan ( 
    SSN numeric(9,0), 
    Student_Loan_ID int, 
    Loan_Term char(10),	-- Might want to modify this later
    Disbursement_Date char(10), 
    Repayment_Start_Date char(10), 
    Repayment_End_Date char(10), 
    Monthly_Payment numeric(10,2), -- check if monthly payment is higher then remainder 
    Grace_Period char (10), -- Years, months, and days 
    Loan_Type char(35), 
    primary key (SSN, Student_Loan_ID), 
    foreign key (SSN) references Client 
);
	-- We might want to add how much was payed
create table Mortgage ( 
  	Mortgage_ID int check (Mortgage_ID >= 0), 
    House_Address char(35), 
    House_Area binary_double check (House_Area >= 0), 
    Number_of_bedrooms numeric(3,0) check (Number_of_bedrooms >= 0), 
    House_Price binary_double check (House_Price >= 0), 
    Loan_Amount binary_double check (Loan_Amount >= 0), 
    Interest_Rate binary_double check (Interest_Rate >= 0), 
    Amount_Paid binary_double default 0 check (Amount_Paid >= 0), 
    Start_Date char(10), 
    Number_of_Payments int default 0 check (Number_of_Payments >= 0), 
    End_Date char(10), 
    primary key (Mortgage_ID) 
);

create table TakesMortgage ( 
    SSN numeric(9,0), 
    Mortgage_ID int, 
    primary key (SSN, Mortgage_ID), 
    foreign key (SSN) references Client, 
    foreign key (Mortgage_ID) references Mortgage 
);

--------------------------------------------------------
-- Test Cases:

insert into Client values (555555555,'Logan',100000);
insert into Mortgage values (1, '123 Main Street, Apt 4B', 2500.0, 3, 350000.0, 300000.0, 3.5, 15000.0, '2023-01-01', 360, '2053-01-01');
insert into TakesMortgage values (555555555, 1);
insert into StudentLoan values (555555555, 123456789, '10 years', '2022-01-15', '2022-07-01', '2032-07-01', 250.00, '6 months', 'Federal Subsidized Loan');
insert into AutoLoan values (22, 'Toyota', 'Camry', 2020, '1HGCM82633A004352', 25000.00, 0.05, 5000.00, '2022-01-01', 60, '2027-01-01');
insert into TakesAutoLoan values (555555555, 22);
insert into PersonalLoan values (33, 'Debt Consolidation', 5000.0, 5.5, 0.0, '2023-04-01', 0, '2024-04-01');
insert into TakesPersonalLoan values (555555555, 33);

select * from Client;
select * from Mortgage;
select * from TakesMortgage;
select * from StudentLoan;
select * from AutoLoan;
select * from TakesAutoLoan;
select * from PersonalLoan;
select * from TakesPersonalLoan;
