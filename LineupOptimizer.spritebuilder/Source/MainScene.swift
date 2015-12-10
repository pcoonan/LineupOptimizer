import Foundation
import UIKit
//import SRWebSocket
//import SocketRocket

class MainScene: CCNode {
    
    func nflButton() {
        let nflLineupScene: CCScene = CCBReader.loadAsScene("nflLineupScene")
        CCDirector.sharedDirector().replaceScene(nflLineupScene);
    }
    
    func nbaButton() {
        let nflLineupScene: CCScene = CCBReader.loadAsScene("nflLineupScene")
        CCDirector.sharedDirector().replaceScene(nflLineupScene);
    }
    
    override func onEnter(){
        super.onEnter()
        connect()
    }

    var inputStream: NSInputStream?
    var outputStream: NSOutputStream?
    
    func connect() {
        
        var readStream : Unmanaged<CFReadStream>?
        var writeStream : Unmanaged<CFWriteStream>?
        
        let mystring = "NFL\r\n"
        
        let data: NSData = mystring.dataUsingEncoding(NSUTF8StringEncoding)!
        
        let host : CFString = NSString(string: "192.168.2.1")
        let port : UInt32 = UInt32(1234)
        
        CFStreamCreatePairWithSocketToHost(nil, host, port, &readStream, &writeStream)
        
        inputStream = readStream!.takeUnretainedValue()
        outputStream = writeStream!.takeUnretainedValue()

//        inputStream!.delegate = self
//        outputStream!.delegate = self
        
        outputStream!.write(UnsafePointer<UInt8>(data.bytes), maxLength: data.length)
        
        inputStream!.scheduleInRunLoop(NSRunLoop.currentRunLoop(), forMode: NSDefaultRunLoopMode)
        outputStream!.scheduleInRunLoop(NSRunLoop.currentRunLoop(), forMode: NSDefaultRunLoopMode)
        
        outputStream!.write(UnsafePointer<UInt8>(data.bytes), maxLength: data.length)
        
        inputStream!.open()
        outputStream!.open()
        
        outputStream!.write(UnsafePointer<UInt8>(data.bytes), maxLength: data.length)
        
        print("\(inputStream!)")
        print("\(outputStream!)")
    }
}
