// ============================================================================ 
// FileReader.h
// ~~~~~~~~
// Patrick Coonan
// ============================================================================ 

#ifndef FileReader_H_
#define FileReader_H_

#include <string>
#include <vector>
#include <Player.h>

class FileReader
{
public:
	FileReader(std::string);
	
	~FileReader();
	std::vector<Player> read();
	bool containsNewLine(std::string);
	std::string firstHalf(std::string value);
	std::string secondHalf(std::string value);
};

#endif