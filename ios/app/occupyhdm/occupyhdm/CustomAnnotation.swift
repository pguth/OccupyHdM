//
//  CustomAnnotation.swift
//  occupyhdm
//
//  Created by Fabian Kugler on 22.03.16.
//  Copyright © 2016 occupyhdm. All rights reserved.
//

import UIKit
import MapKit

class CustomAnnotation: NSObject, MKAnnotation {
    var coordinate: CLLocationCoordinate2D
    var title: String?
    var name: String?
    var owner: String?
    
    init(coordinate : CLLocationCoordinate2D, name : String, owner : String)
    {
        self.coordinate = coordinate
        self.title = name + " - Owner: " + owner
        self.name = name
        self.owner = owner
    }
}