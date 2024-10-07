#include<iostream>
#include<fstream>
#include<windows.h>
#include<sstream>
using namespace std;

class Hostel{
    private:
    string Name;
    int Rent, Bed;
    public:
    Hostel(string name, int rent, int bed)
    {
        Name = name;
        Rent = rent;
        Bed = bed;
    }

    string getName()
    {
        return Name;
    }
    int getRent()
    {
        return Rent;
    }
    int getBed()
    {
        return Bed;
    }

    void reserve()
    {
        ifstream in("C:/Users/priya/Documents/HAS/Hostel.txt");
        ofstream out("C:/Users/priya/Documents/HAS/Hostel Temp.txt");

        string line;
        while(getline(in, line))
        {
            int pos = line.find("Gomukh Hostel");
            if(pos != string::npos)
            {
                int bed = Bed -1;
                Bed = bed;

                stringstream ss;
                ss << bed;
                string strBed = ss.str();

                int bedPos = line.find_last_of(':');
                line.replace(bedPos+1, string::npos, strBed);
            }
            out<<line<<endl;
        }
        out.close();
        in.close();
        remove("C:/Users/priya/Documents/HAS/Hostel.txt");
        rename("C:/Users/priya/Documents/HAS/Hostel Temp.txt", "C:/Users/priya/Documents/HAS/Hostel.txt");
        cout<<"\tBed reserved successfully!"<<endl;
    }
};

class Student{

    private:
    string Name, RollNo, Address;

    public:
    Student():Name(""),RollNo(""),Address(""){}

    void setName(string name)
    {
        Name = name;
    }
    void setRollNo(string rollNo)
    {
        RollNo = rollNo;
    }
    void setAddress(string address)
    {
        Address = address;
    }

    string getName()
    {
        return Name;
    }
    string getRollNo()
    {
        return RollNo;
    }
    string getAddress()
    {
        return Address;
    }
};

int main(){

    Hostel h("Gomukh Hostel", 16500, 3);

    ofstream out("C:/Users/priya/Documents/HAS/Hostel.txt");
    out<<"\t"<<h.getName()<<" : "<<h.getRent()<<" : "<<h.getBed()<<endl;
    cout<<"Hostel data saved successfully!"<<endl;
    out.close();
    Student s;

    bool exit = false;

    while(!exit)
    {
        system("cls");
        int val;
        cout<<"\tWELCOME TO HOSTEL ACCOMODATION SYSTEM"<<endl;
        cout<<"\t*************************************"<<endl;
        cout<<"\t1) Reserve a Bed."<<endl;
        cout<<"\t2) Exit"<<endl;
        cout<<"\tEnter Choice : "<<endl;
        cin >> val;

        if(val == 1)
        {
            system("cls");
            string name, rollNo, address;

            cout<<"\tEnter name of Student : ";
            cin >> name;
            s.setName(name);

            cout<<"\tEnter RollNo of the Student : ";
            cin >> rollNo;
            s.setRollNo(rollNo);

            cout<<"\tEnter Address of the Student : ";
            cin >> address;
            s.setRollNo(address);

            if(h.getBed() > 0){
                
                h.reserve();
            }
            else if(h.getBed() == 0){

                cout<<"\tSorry, Bed not available"<<endl;
            }

            ofstream outFile("C:/Users/priya/Documents/HAS/Student.txt", ios::app);
            outFile<<"\t"<<s.getName()<<" : "<<s.getRollNo()<<" : "<<s.getAddress()<<endl;
            Sleep(5000);
            }

            else if(val == 2){

                system("cls");
                exit = true;
                cout<<"Good Luck!"<<endl;
                Sleep(3000);
            }
        }
}