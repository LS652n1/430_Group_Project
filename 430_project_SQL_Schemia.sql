drop table TakesAutoLoan;
drop table AutoLoan;

drop table TakesPersonalLoan;
drop table PersonalLoan;

drop table StudentLoan;

drop table TakesMortgage;
drop table Mortgage;

drop table Client;

-- Might want to put a check on all the id's so they are at a billion minimum

create table Client (
    SSN numeric(9,0) check (001010001 <= SSN),
    Name char (50),	-- This might be a combination of first, middle, last, and suffix
	Income binary_double,

    primary key (SSN)
);

create table AutoLoan (
    Auto_Loan_ID int,
    Make char(20),
    Model char(20),
	Year int check (1970 <= Year AND Year <= 2030),
    VIN char(17),
    Loan_Amount binary_double,
    Interest_Rate binary_double,
    Amount_Paid binary_double, --check (Amount_Paid <= Loan_Amount), -- Change this later, to the total value
    Start_Date char(10),		-- Might change this to date later
    Number_of_payments int,
	End_Date char (10), 		-- check (Start_Date <= End_Date),
    primary key (Auto_Loan_ID)
);

-- Might need a check here for SSN
create table TakesAutoLoan (
    SSN numeric(9,0),
    Auto_Loan_ID int,
    primary key (SSN,Auto_Loan_ID),
    foreign key (SSN) references Client,
    foreign key (Auto_Loan_ID) references AutoLoan
);

create table PersonalLoan (
    Personal_Loan_ID int,
    SSN numeric(9,0),
    Loan_purpose char(20),
    Loan_Amount binary_double,
    Interest_Rate binary_double,
    Amount_Paid binary_double, 		-- check Amount <= total value
	Start_Date char(10),
    Number_of_Payments int,
    End_Date char(10),				-- check Start_Date <= End_Date
	primary key (Personal_Loan_ID),
    foreign key (SSN) references Client
);
create table TakesPersonalLoan (
    SSN numeric(9,0),
    Personal_Loan_ID int,
    primary key (SSN,Personal_Loan_ID),
    foreign key (SSN) references Client,
    foreign key (Personal_Loan_ID) references PersonalLoan
);

create table StudentLoan (
    Student_Loan_ID int,
    SSN numeric(9,0),
    Loan_Term char(10),	-- Years, months, and days
    Disbursement_Date char(10),
    Repayment_Start_Date char(10),
    Repayment_End_Date char(10),
    Monthly_Payment numeric(10,2), -- check if monthly payment is higher then remainder
    Grace_Period char (10), -- Years, months, and days
    Loan_Type char(20),
    primary key (SSN, Student_Loan_ID),
    foreign key (SSN) references Client
);

create table Mortgage (
  	Mortgage_ID int check (Mortgage_ID >= 0),
    House_Address int check(House_Address >= 0),
    House_Area char(20),
    Number_of_bedrooms numeric(3,0) check (Number_of_bedrooms >= 0),
    House_Price binary_double check (House_Price >= 0),
    Loan_Amount binary_double check (Loan_Amount >= 0),
    Interest_Rate binary_double check (Interest_Rate >= 0),
    Amount_Paid binary_double check (Amount_Paid >= 0),
    Start_Date char(10),
    Number_of_Payments int check (Number_of_Payments >= 0),
    End_Date char(10),	-- check Start_Date <= End_Date
    primary key (Mortgage_ID)
);
create table TakesMortgage (
    SSN numeric(9,0),
    Mortgage_ID int,
    primary key (SSN, Mortgage_ID),
    foreign key (SSN) references Client
);

------------------------------------------------
insert into Client values (555555555,'Logan',100000);

