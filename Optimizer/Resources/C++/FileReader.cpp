#include "FileReader.h"
#include <string>
#include <fstream>
#include <iostream>
#include <vector>
#include <Player.h>

using namespace std;

ifstream file;

FileReader::FileReader(string filename)
{
	file = ifstream(filename); 
}

vector<Player> FileReader::read()
{
	vector<Player> pVec;
	int x = 0;
	string value;
	string pos;
	string name;
	int salary;
	int ppg;
	while (file.good())
	{
		switch (x){
		case 0:
			getline(file, value, ',');
			if (value.compare("\"Position\"") == 0 || value.compare("\"Name\"") == 0 ||
				value.compare("\"Salary\"") == 0 || value.compare("\"GameInfo\"") == 0 ||
				value.compare("\"AvgPointsPerGame\"") == 0){
				break;
			}
			else if (containsNewLine(value)){
				string temp = firstHalf(value);
				if (temp.compare("\"AvgPointsPerGame\"") == 0){
					value = secondHalf(value);
					pos = value;
					x = 1;
					break;
				}
				else{

				}
			}
			pos = string(value, 1, value.length() - 2);
			x = 1;
			break;
		case 1:
			getline(file, value, ',');
			name = string(value, 1, value.length() - 2);
			x = 2;
			break;
		case 2:
			getline(file, value, ',');
			salary = atoi(value.c_str());
			x = 3;
			break;
		case 3:
			getline(file, value, ',');
			x = 4;
			break;
		case 4:
			getline(file, value, ',');
			if (containsNewLine(value)){
				string temp = firstHalf(value);
				ppg = atoi(value.c_str());
				Player* newP = new Player(pos, name, salary, ppg);
				pVec.push_back(*newP);
				pos = secondHalf(value);
				x = 1;
				break;
			}
			ppg = atoi(value.c_str());
			
			Player p(pos, name, salary, ppg);
			pVec.push_back(p);

			x = 1;
			break;
		}
	}

	//for (vector<Player>::iterator it = pVec.begin(); it != pVec.end(); ++it){
	//	//Player p = *it;
	//	cout << (*it).print() << endl; //p.print() << endl;
	//}

	return pVec;
}

FileReader::~FileReader()
{
}





bool FileReader::containsNewLine(string value)
{
	for (unsigned int x = 0; x < value.length(); x++){
		if (value[x] == '\n') return true;
	}
	return false;
}


string FileReader::firstHalf(string value)
{
	string temp;
	for (unsigned int i = 0; i < value.length(); i++){
		if (value[i] == '\n') break;
		temp += value[i];
	}
	return temp;
}


string FileReader::secondHalf(string value)
{
	string temp;
	bool nl = false;
	for (unsigned int i = 0; i < value.length(); i++){
		if(nl) temp += value[i];
		if (value[i] == '\n') nl = true;
	}
	if(temp.length() != 0)
		temp = string(temp, 1, temp.length() - 2);
	return temp;
}
