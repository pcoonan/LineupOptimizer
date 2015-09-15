#include "NBA_Analyzer.h"
#include <vector>
#include "Player.h"
#include <iostream>
#include <cstdio>
#include <ctime>
#include <thread>

using namespace std;

vector<Player> pg;
vector<Player> sg;
vector<Player> sf;
vector<Player> pf;
vector<Player> c;
int total;
int prog;
int prepercent = -1;
float percent;

NBA_Analyzer::NBA_Analyzer()
{

}

NBA_Analyzer::NBA_Analyzer(vector<Player>& pVec)
{
	pg = fillPos(pVec, "PG");
	sg = fillPos(pVec, "SG");
	sf = fillPos(pVec, "SF");
	pf = fillPos(pVec, "PF");
	c = fillPos(pVec, "C");
	total = totalCombination();
	prog = 0;
	cout << pVec.size() << " players added: Ready to create optimal lineup." << endl;
}


NBA_Analyzer::~NBA_Analyzer()
{
}


vector<Player> NBA_Analyzer::fillPos(vector<Player> players, string pos)
{
	vector<Player> temp;
	for (int i = 0; i < players.size(); i++){
		string ppos = players[i].position();
		string name = players[i].pname();
		if (players[i].position().compare(pos) == 0){
			temp.push_back(players[i]);
		}
	}
	return temp;
}


void NBA_Analyzer::print_pos(std::string pos)
{
	if (pos.compare("PG") == 0){
		for (vector<Player>::iterator it = pg.begin(); it != pg.end(); ++it){
			Player p = *it;
			cout << p.print() << endl;
		}
		cout << pg.size() << " PG total." << endl;
	}
	else if (pos.compare("SG") == 0){
		for (vector<Player>::iterator it = sg.begin(); it != sg.end(); ++it){
			Player p = *it;
			cout << p.print() << endl;
		}
		cout << sg.size() << " SG total." << endl;
	}
	else if (pos.compare("SF") == 0){
		for (vector<Player>::iterator it = sf.begin(); it != sf.end(); ++it){
			Player p = *it;
			cout << p.print() << endl;
		}
		cout << sf.size() << " SF total." << endl;
	}
	else if (pos.compare("PF") == 0){
		for (vector<Player>::iterator it = pf.begin(); it != pf.end(); ++it){
			Player p = *it;
			cout << p.print() << endl;
		}
		cout << pf.size() << " PF total." << endl;
	}
	else if (pos.compare("C") == 0){
		for (vector<Player>::iterator it = c.begin(); it != c.end(); ++it){
			Player p = *it;
			cout << p.print() << endl;
		}
		cout << c.size() << " C total." << endl;
	}
	else{
		cout << "Error: Please enter PG/SG/SF/PF/C" << endl;
	}
}


vector<Player*> NBA_Analyzer::optimize()
{
	vector<vector<Player>> lineups;
	vector<Player*> curList = vector<Player*>();
	vector<Player*> best = vector<Player*>();

	clock_t start;
	double duration;

	start = std::clock();

	findPG(curList, best, 50000, 0);
	for (vector<Player*>::iterator it = best.begin(); it != best.end(); ++it){
		cout << (*it)->name << endl;
	}

	duration = (clock() - start) / (double)CLOCKS_PER_SEC;

	cout << "Time elapsed: " << duration << endl;
	return best;
}


bool NBA_Analyzer::findPG(vector<Player*>& curList, vector<Player*>& bestList, int salary, int ppg)
{
	for (vector<Player>::iterator it = pg.begin(); it != pg.end(); ++it){
		Player* p = &*it;
		if (p->salary > salary)
			continue;
		//cout << p->name << endl;
		curList.push_back(p);
		findSG(curList, bestList, salary - p->salary, ppg + p->ppg);
		curList.pop_back();
	}
	return false;
}


bool NBA_Analyzer::findSG(vector<Player*>& curList, vector<Player*>& bestList, int salary, int ppg)
{
	int temp = 0;
	vector<thread> threadList;
	for (vector<Player>::iterator it = sg.begin(); it != sg.end(); ++it){
		Player* p = &*it;
		if (p->salary > salary)
			continue;
		cout << p->name << endl;
		curList.push_back(p);
		threadList.push_back(thread(&findSF, ref(curList), ref(bestList), salary - p->salary, ppg + p->ppg));
		//findSF(curList, bestList, salary - p->salary, ppg + p->ppg);
		curList.pop_back();
	}
	for (vector<thread>::iterator it = threadList.begin(); it != threadList.end(); ++it){
		if ((*it).joinable()){
			(*it).join();
		}
		else{
			(*it).detach();
		}
	}
	return false;
}


bool NBA_Analyzer::findSF(vector<Player*>& curList, vector<Player*>& bestList, int salary, int ppg)
{
	for (vector<Player>::iterator it = sf.begin(); it != sf.end(); ++it){
		Player* p = &*it;
		if (p->salary > salary)
			continue;
		cout << p->name << endl;
		curList.push_back(p);
		findPF(curList, bestList, salary - p->salary, ppg + p->ppg);
		curList.pop_back();
	}
	return false;
}


bool NBA_Analyzer::findPF(vector<Player*>& curList, vector<Player*>& bestList, int salary, int ppg)
{
	for (vector<Player>::iterator it = pf.begin(); it != pf.end(); ++it){
		Player* p = &*it;
		if (p->salary > salary)
			continue;
		//cout << p->name << endl;
		curList.push_back(p);
		findC(curList, bestList, salary - p->salary, ppg + p->ppg);
		curList.pop_back();
	}
	return false;
}


bool NBA_Analyzer::findC(vector<Player*>& curList, vector<Player*>& bestList, int salary, int ppg)
{
	for (vector<Player>::iterator it = c.begin(); it != c.end(); ++it){
		Player* p = &*it;
		if (p->salary > salary)
			continue;
		//cout << p->name << endl;
		curList.push_back(p);
		findG(curList, bestList, salary - p->salary, ppg + p->ppg);
		curList.pop_back();
	}
	return false;
}


bool NBA_Analyzer::findG(vector<Player*>& curList, vector<Player*>& bestList, int salary, int ppg)
{
	for (vector<Player>::iterator it = pg.begin(); it != pg.end(); ++it){
		Player* p = &*it;
		if (p->salary > salary || contains(p, curList)) // check to see if pg already in curList
			continue;
		curList.push_back(p);
		findF(curList, bestList, salary - p->salary, ppg + p->ppg);
		curList.pop_back();
	}
	for (vector<Player>::iterator it = sg.begin(); it != sg.end(); ++it){
		Player* p = &*it;
		if (p->salary > salary || contains(p, curList)) // check to see if sg already in curList
			continue;
		curList.push_back(p);
		findF(curList, bestList, salary - p->salary, ppg + p->ppg);
		curList.pop_back();
	}
	return false;
}


bool NBA_Analyzer::findF(vector<Player*>& curList, vector<Player*>& bestList, int salary, int ppg)
{
	for (vector<Player>::iterator it = sf.begin(); it != sf.end(); ++it){
		Player* p = &*it;
		if (p->salary > salary || contains(p, curList)) // check to see if sf already in curList
			continue;
		curList.push_back(p);
		findUtil(curList, bestList, salary - p->salary, ppg + p->ppg);
		curList.pop_back();
	}
	for (vector<Player>::iterator it = pf.begin(); it != pf.end(); ++it){
		Player* p = &*it;
		if (p->salary > salary || contains(p, curList))	// check to see if pf already in curList
			continue;
		curList.push_back(p);
		findUtil(curList, bestList, salary - p->salary, ppg + p->ppg);
		curList.pop_back();
	}
	return false;
}


bool NBA_Analyzer::findUtil(vector<Player*>& curList, vector<Player*>& bestList, int salary, int ppg)
{
	for (vector<Player>::iterator it = pg.begin(); it != pg.end(); ++it){
		prog++;
		Player* p = &*it;
		if (p->salary > salary || contains(p, curList)) // check to see if pg already in curList
			continue;
		curList.push_back(p);
		if (ppg + p->ppg > scoreList(bestList))
			makeBest(curList, bestList);
		curList.pop_back();
	}
	for (vector<Player>::iterator it = sg.begin(); it != sg.end(); ++it){
		prog++;
		Player* p = &*it;
		if (p->salary > salary || contains(p, curList))	// check to see if sg already in curList
			continue;
		curList.push_back(p);
		if (ppg + p->ppg > scoreList(bestList))
			makeBest(curList, bestList);
		curList.pop_back();
	}
	for (vector<Player>::iterator it = sf.begin(); it != sf.end(); ++it){
		prog++;
		Player* p = &*it;
		if (p->salary > salary || contains(p, curList))	// check to see if sf already in curList
			continue;
		curList.push_back(p);
		if (ppg + p->ppg > scoreList(bestList))
			makeBest(curList, bestList);
		curList.pop_back();
	}
	for (vector<Player>::iterator it = pf.begin(); it != pf.end(); ++it){
		prog++;
		Player* p = &*it;
		if (p->salary > salary || contains(p, curList))	// check to see if pf already in curList
			continue;
		curList.push_back(p);
		if (ppg + p->ppg > scoreList(bestList))
			makeBest(curList, bestList);
		curList.pop_back();
	}
	for (vector<Player>::iterator it = c.begin(); it != c.end(); ++it){
		prog++;
		Player* p = &*it;
		if (p->salary > salary || contains(p, curList)) // check to see if c already in curList
			continue;
		curList.push_back(p);
		if (ppg + p->ppg > scoreList(bestList))
			makeBest(curList, bestList);
		curList.pop_back();
	}
	//cout << "Current progress: " << int(percent * 100) << " %\r";
	//cout << '\r' << "Current progress: " << prog << "/" << total;
	percent = prog / total;
	if (int(percent * 100) != prepercent){
		cout << '\r' << "Current progress: " << int(percent * 100) << "%";
		prepercent = percent;
	}
	
	return false;
}


bool NBA_Analyzer::contains(Player*& player, vector<Player*>& list)
{
	for (vector<Player*>::iterator it = list.begin(); it != list.end(); ++it){
		Player p = **it;
		if (p.name.compare(player->name) == 0)
			return true;
	}
	return false;
}


void NBA_Analyzer::makeBest(vector<Player*>& curList, vector<Player*>& bestList)
{
	/*for (vector<Player*>::iterator it = bestList.begin(); it != bestList.end(); ++it){
		Player* p = *it;
	}*/
	bestList.clear();
	for (vector<Player*>::iterator it = curList.begin(); it != curList.end(); ++it){
		bestList.push_back(*it);
	}
}


int NBA_Analyzer::scoreList(vector<Player*>& list)
{
	int ret = 0;
	for (vector<Player*>::iterator it = list.begin(); it != list.end(); ++it){
		ret += (**it).ppg;
	}
	return ret;
}


int NBA_Analyzer::totalCombination()
{
	int ret = pg.size() * sg.size() * sf.size() * pf.size() * c.size() * (pg.size() + sg.size() - 2)
		* (sf.size() + pf.size() - 2)* (pg.size() + sg.size() + sf.size() + pf.size() + c.size() - 7);
	return ret;
}
