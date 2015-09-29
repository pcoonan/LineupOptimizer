#include "FileReader.h"
#include "NBA_Analyzer.h"
#include "NFL_Analyzer.h"
#include "Player.h"
#include <cstdlib>   // for exit(0)
#include <iostream>
#include <map>
#include <vector>
#include <sstream>
#include <stdexcept>
#include <boost/lambda/lambda.hpp>
#include <iterator>
#include <algorithm>
#include <thread>

using namespace std;

void prompt();
typedef void(*cmd_t)(istringstream&);
void bye(istringstream&);
void load_lineup(istringstream&);
void print_cmd(istringstream&);
void sort_cmd(istringstream&);
void remove_cmd(istringstream&);
void total_cmd(istringstream&);
void test(istringstream&);
void threadFunc();

vector<Player> pVec;
NBA_Analyzer opt;
NFL_Analyzer nfl_opt;

int main(){
	map<string, cmd_t> cmd_map;
	cmd_map["exit"] = &bye;
	cmd_map["bye"] = &bye;
	cmd_map["quit"] = &bye;
	cmd_map["load"] = &load_lineup;
	cmd_map["print"] = &print_cmd;
	cmd_map["sort"] = &sort_cmd;
	cmd_map["remove"] = &remove_cmd;
	cmd_map["rm"] = &remove_cmd;
	cmd_map["total"] = &total_cmd;
	cmd_map["test"] = &test;

	cout << "Welcome to the Lineup Optimizer" << endl;

	istringstream iss;
	string cmd, line;
	while (cin) {
		prompt();
		getline(cin, line);
		iss.clear();
		iss.str(line);
		if (!(iss >> cmd))
			continue;

		if (cmd_map.find(cmd) != cmd_map.end()) {
			try {
				cmd_map[cmd](iss);
			}
			catch (runtime_error &e) {
				cout << e.what() << endl;
			}
		}
		else {
			cout << "Unknown command" << endl;
		}
	}
	return 0;

	
}

void threadFunc()
{
	cout << "Welcome to Multithreading" << endl;

}

void test(istringstream& iss)
{
	thread funcTest1(threadFunc);
	funcTest1.join();







	//vector<Player> testVec;
	//Player* p1 = new Player();
	//p1->name = "Bob";
	//p1->pos = "C";
	//p1->ppg = 50;
	//p1->salary = 50;
	//Player* p2 = new Player("SF", "Jon", 500, 75);
	//testVec.push_back(*p1);
	//testVec.push_back(*p2);
	//vector<Player>::iterator it;
	//for (it = testVec.begin(); it != testVec.end(); ++it){
	//	//Player p = *it;
	//	cout << (*it).print() << endl; //p.print() << endl;
	//	cout << (*it).name << endl;
	//}
	
}

void bye(istringstream& iss)
{
	exit(0);
}

void load_lineup(istringstream& iss)
{
	string type, name, tmp;
	if (!(iss >> type) || !(iss >> name) || (iss >> tmp))
		throw runtime_error("Syntax: load [filename]");
	FileReader fr(name);
	pVec = fr.read();
	
	if (type.compare("nba") == 0)
		opt = NBA_Analyzer(pVec);
	else
		nfl_opt = NFL_Analyzer(pVec);			// NOT YET IMPLEMENTED
}

void print_cmd(istringstream& iss)
{
	//cout << "Not yet implemented" << endl;
	string name, tmp;
	if (!(iss >> name) || (iss >> tmp))
		throw runtime_error("Syntax: print [position]");
	opt.print_pos(name);
}

void sort_cmd(istringstream& iss)
{
	cout << "Here we go..." << endl;
	opt.optimize();
}

void remove_cmd(istringstream& iss)
{
	cout << "Not yet implemented" << endl;
}

void total_cmd(istringstream& iss)
{
	cout << opt.totalCombination() << endl;
}

void prompt()
{
	cout << "> " << flush;
}