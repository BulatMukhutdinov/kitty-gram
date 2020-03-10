import Combine
import SwiftUI
import shared

final class KittyListViewModel: ObservableObject {
    
    @Published
    var kitties = [Kitty]()
    
    private let interactor = DiKt.getKittyListDomain()
    
    init() {
        refresh()
    }
    
    func refresh() {
        interactor.getAll { kitties in
            self.kitties = kitties
        }
    }
}
