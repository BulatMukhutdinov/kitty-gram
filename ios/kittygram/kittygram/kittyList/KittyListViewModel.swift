import Combine
import SwiftUI
import shared

final class KittyListViewModel: ObservableObject {
    
    @Published
    var kitties = [Kitty_]()
    
    private let interactor = DiKt.getKittyListDomain()
    
    init() {
        interactor.getAll { kitties in
            self.kitties = kitties
        }
    }
    
    func refresh() {
        interactor.refresh { kitties in
            self.kitties = kitties
        }
    }
}
